package com.adobe.aem.Vaikuntha.core.servlets;
import java.io.IOException;

import javax.servlet.Servlet;

import com.adobe.aem.Vaikuntha.core.services.OllamaClient;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = Servlet.class,
        property = {
                ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/ollama/chat",
                ServletResolverConstants.SLING_SERVLET_METHODS + "=POST"
        }
)
public class OllamaChatServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(OllamaChatServlet.class);

    private static final long serialVersionUID = 1L;

    @Reference
    private OllamaClient ollamaClient;


    @Override
    protected void doPost(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws IOException {

        String prompt = request.getParameter("prompt");

        if (prompt == null || prompt.isEmpty()) {
            log.info("Ollama request received with missing prompt");
            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Missing prompt\"}");
            return;
        }
        log.info("Sending prompt to Ollama");
        try {
            String result = ollamaClient.generate(prompt);
            log.info("Received response from Ollama");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);

        } catch (Exception e) {
            log.error("Error while calling Ollama service", e);
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter()
                    .write("{\"error\":\"Ollama call failed\"}");
        }
    }

}
