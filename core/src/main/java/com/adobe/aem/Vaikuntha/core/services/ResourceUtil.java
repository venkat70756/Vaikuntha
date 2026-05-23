package com.adobe.aem.Vaikuntha.core.services;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Map;

@Component(service = ResourceUtil.class)
public class ResourceUtil {

    private String USER_NAME = "Ali";

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    public ResourceResolver getResolver() throws LoginException {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, USER_NAME);

        return resourceResolverFactory.getServiceResourceResolver(param);
    }
}
