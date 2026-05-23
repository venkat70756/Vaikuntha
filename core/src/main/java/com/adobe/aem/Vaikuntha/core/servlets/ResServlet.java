package com.adobe.aem.Vaikuntha.core.servlets;

import com.adobe.aem.Vaikuntha.core.services.ResExample;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
        property = {
            "sling.servlet.paths=/bin/osgicalling",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        }
)
public class ResServlet extends SlingSafeMethodsServlet {

    @Reference
    ResExample resExample;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException{
        resExample.accessingResource();
    }
}
