package com.adobe.aem.Vaikuntha.core.servlets;

import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class,
        property = {
            "sling.servlet.paths=/bin/testtwo",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.methods=" + HttpConstants.METHOD_POST
        }
)
public class TestServletTwo extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(TestServletTwo.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException, NullPointerException{
        log.info("Get method is working");

        String title;
        String redirectionPath;
        Map<String , String > testData = new HashMap();

        String pp = request.getParameter("PagePath");

        if (pp == null || pp.isEmpty()) {
            log.warn("PagePath parameter is missing");
            response.setStatus(400);
            response.getWriter().write("PagePath parameter is required");
            return;
        }


        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(pp);
        log.info("Resource is: {}", resource);
        Resource contentResource = resource.getChild("jcr:content");

        if (contentResource != null) {
            title = contentResource.getValueMap().get("jcr:title", String.class);
            redirectionPath = resource.getPath();
            testData.put(title, redirectionPath);
            log.info("The page title is: {}, Redirection Path {}", title, redirectionPath);
        } else {
            log.warn("jcr:content node not found");
        }

        Gson gson = new Gson();

        String jsonRespone = gson.toJson(testData);

        response.setContentType("application/json");
        response.getWriter().write(jsonRespone);

    }



    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException{
        log.info("POST method is working");
    }
}
