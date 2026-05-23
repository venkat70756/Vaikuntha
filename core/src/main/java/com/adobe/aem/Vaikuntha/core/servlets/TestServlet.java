package com.adobe.aem.Vaikuntha.core.servlets;

import com.adobe.aem.Vaikuntha.core.services.impl.DemoTwoServiceImpl;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class,
        property = {
            "sling.servlet.paths=/bin/test",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        }
)
public class TestServlet extends SlingSafeMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(TestServlet.class);

        @Override
        protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException{
            log.trace("Trace");
            log.debug("Debug");
            log.info("Info");
            log.warn("Warn");
            log.error("Error");

        }
}
