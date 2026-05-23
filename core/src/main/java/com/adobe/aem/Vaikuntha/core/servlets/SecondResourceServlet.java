package com.adobe.aem.Vaikuntha.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class,
        property = {
            "sling.servlet.resourceTypes=Vaikuntha/components/title",
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                "sling.servlet.selectors=venkat",
                "sling.servlet.extensions=html"

        }
)
public class SecondResourceServlet extends SlingSafeMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(SecondResourceServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException{
        PrintWriter printWriter = response.getWriter();
        printWriter.println("This is from Resource based servlet");
        log.info("updated logger");

    }

}
