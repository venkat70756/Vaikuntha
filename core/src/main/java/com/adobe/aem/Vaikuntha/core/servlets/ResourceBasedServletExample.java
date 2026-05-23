package com.adobe.aem.Vaikuntha.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
            "sling.servlet.resourceTypes=Vaikuntha/components/snehal/hero",
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                "sling.servlet.selectors=data",
                "sling.servlet.extensions=html"
        }
)
public class ResourceBasedServletExample extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(ResourceBasedServletExample.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException , ServletException{
        log.info("Resource Based Get method triggered");
    }
}
