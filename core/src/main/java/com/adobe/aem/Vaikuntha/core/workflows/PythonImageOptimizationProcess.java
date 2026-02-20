package com.adobe.aem.Vaikuntha.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Binary;
import javax.jcr.Session;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component(
        service = WorkflowProcess.class,
        property = {
                "process.label=Image Optimizing Using Python"
        }
)
public class PythonImageOptimizationProcess implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(PythonImageOptimizationProcess.class);

    private static final String OPTIMIZED_FLAG = "videoSays:imageOptimized";

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args)
            throws WorkflowException {

        WorkflowData data = item.getWorkflowData();
        String assetPath = data.getPayload().toString();

        // Normalize DAM workflow payload to asset path
        if (assetPath.contains("/jcr:content")) {
            assetPath = assetPath.substring(0, assetPath.indexOf("/jcr:content"));
        }

        LOG.info("Starting Python image optimization workflow for asset: {}", assetPath);

        Map<String, Object> authInfo = new HashMap<>();
        authInfo.put(ResourceResolverFactory.SUBSERVICE, "workflow-service");

        try (ResourceResolver resolver =
                     resolverFactory.getServiceResourceResolver(authInfo)) {

            Resource resource = resolver.getResource(assetPath);
            if (resource == null) {
                throw new WorkflowException("Resource not found: " + assetPath);
            }

            Asset asset = resource.adaptTo(Asset.class);
            if (asset == null) {
                throw new WorkflowException("Asset not found: " + assetPath);
            }

            // Check metadata flag
            Resource metadataRes = resolver.getResource(
                    assetPath + "/jcr:content/metadata");

            if (metadataRes != null) {
                ValueMap vm = metadataRes.getValueMap();
                if (vm.get(OPTIMIZED_FLAG, false)) {
                    LOG.info("Asset already optimized, skipping: {}", assetPath);
                    return;
                }
            }

            File input = File.createTempFile("aem-input-", ".img");
            File output = File.createTempFile("aem-output-", ".img");

            try (InputStream is = asset.getOriginal().getStream();
                 FileOutputStream fos = new FileOutputStream(input)) {
                IOUtils.copy(is, fos);
            }

            runPythonOptimizer(input, output);

            // 🚫 Size check removed. We always update the asset
            AssetManager assetManager = resolver.adaptTo(AssetManager.class);
            Session session1 = resolver.adaptTo(Session.class);

            try (InputStream optimizedStream = new FileInputStream(output)) {
                Binary binary = session1.getValueFactory().createBinary(optimizedStream);

                assetManager.createOrUpdateAsset(
                        assetPath,
                        binary,
                        asset.getMimeType(),
                        true
                );
            }

            if (metadataRes != null) {
                ModifiableValueMap mvm = metadataRes.adaptTo(ModifiableValueMap.class);
                if (mvm != null) {
                    mvm.put(OPTIMIZED_FLAG, true);
                    resolver.commit();
                }
            }

            LOG.info("Image optimized successfully: {}", assetPath);

        } catch (Exception e) {
            LOG.error("Python image optimization failed", e);
            throw new WorkflowException(e);
        }
    }

    /**
     * Extracts optimize_image.py from bundle to a temp file
     */
    private File extractPythonScript() throws IOException {

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("python/optimize_image.py");

        if (is == null) {
            throw new FileNotFoundException("optimize_image.py not found in bundle");
        }

        File script = File.createTempFile("optimize_image", ".py");

        try (FileOutputStream fos = new FileOutputStream(script)) {
            IOUtils.copy(is, fos);
        }

        script.setExecutable(true);
        return script;
    }

    private void runPythonOptimizer(File input, File output) throws Exception {

        File script = extractPythonScript();

        ProcessBuilder pb = new ProcessBuilder(
                "python",
                script.getAbsolutePath(),
                input.getAbsolutePath(),
                output.getAbsolutePath()
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                LOG.info("[PYTHON] {}", line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Python script failed with exit code " + exitCode);
        }
    }
}
