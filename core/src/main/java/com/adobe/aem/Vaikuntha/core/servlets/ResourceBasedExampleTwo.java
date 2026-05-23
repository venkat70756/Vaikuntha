package com.adobe.aem.Vaikuntha.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
@Component(service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/pageInfoUpdate",    // /bin/pageInfoUpdate
                "sling.servlet.methods="+ HttpConstants.METHOD_GET,
                "sling.servlet.selectors=dataTwo",
                "sling.servlet.extensions=html"
        }
)
public class ResourceBasedExampleTwo  extends SlingAllMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(ResourceBasedExampleTwo.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException, ServletException {
        log.info("Second Resource Based Get method triggered");

        String pagePath = request.getParameter("pagePath");

        ResourceResolver resourceResolver = request.getResourceResolver();


        try {
            Resource resource = resourceResolver.getResource(pagePath + "/jcr:content"); ///content/Vaikuntha/us/en/jcr:content
            if (resource != null) {

                Node node = resource.adaptTo(Node.class);

                if (node != null) {
                    node.setProperty("authorName", "snehal");
                    node.setProperty("books", new String[]{"Book1", "Book2"});


                    Session session = resourceResolver.adaptTo(Session.class);
                    session.save();

                    response.getWriter().write("Properties added using Node");
                }
                else {
                    log.info("Node is empty");
                }
            }
            else {
                log.info("Resource is empty");
            }

        } catch (RepositoryException e) {
            log.error("Error is" +e);
        }
    }
}
