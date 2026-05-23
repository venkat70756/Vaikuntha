package com.adobe.aem.Vaikuntha.core.services.impl;

import com.adobe.aem.Vaikuntha.core.services.ResExample;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = ResExample.class, immediate = true)
public class ResExampleImpl implements ResExample {

    private static final Logger log = LoggerFactory.getLogger(ResExampleImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;


    @Override
    public void accessingResource() {

        ResourceResolver resourceResolver =null;

        try {

            //part -1  Creating ResourceResolverFactory with Service User
            Map<String, Object> param = new HashMap<>();
            param.put(ResourceResolverFactory.SUBSERVICE,"venkat");

            resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);


            //Part -2 Reading resource
            Resource resource = resourceResolver.getResource("/content/Vaikuntha/us/en/test/jcr:content");
            if (resource != null){
                ValueMap prop = resource.getValueMap();
                String title = prop.get("jcr:title", String.class);

                log.info("Title is:{}", title);

            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
