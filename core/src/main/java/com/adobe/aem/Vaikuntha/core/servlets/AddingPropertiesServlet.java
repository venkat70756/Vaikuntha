package com.adobe.aem.Vaikuntha.core.servlets;

import com.adobe.aem.Vaikuntha.core.services.ResourceUtil;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/addingProperties",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET
} )
public class AddingPropertiesServlet extends SlingSafeMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(AddingPropertiesServlet.class);

    @Reference
    ResourceUtil resourceUtil;

    @Override
    protected void doGet(SlingHttpServletRequest  request, SlingHttpServletResponse response) throws IOException, ServletException{

        String pagePath = request.getParameter("pagePath");

        log.info("Page Path from Query Param: {}", pagePath);
//        ResourceResolver resourceResolver= request.getResourceResolver();




        try(ResourceResolver resourceResolver = resourceUtil.getResolver()){

            Resource resource = resourceResolver.getResource(pagePath+"/jcr:content");

            if (resource != null){
                Node  node = resource.adaptTo(Node.class);

                if (node != null){

                    ValueMap valueMap = resource.adaptTo(ValueMap.class);
                    String pageName = valueMap.get("jcr:title",String.class);
                    log.info("the Page Jcr title is : {} ",pageName);


                    node.setProperty("TrainingClass","AEM Class");
                    node.setProperty("AEM Student", 1);

                    Session  session = resourceResolver.adaptTo(Session.class);
                    session.save();


                }else {
                    log.warn(pagePath+"/jcr:content not found");
                }
            }
            else{
                log.warn("Resource not found at {}",pagePath);
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }


    }

}
