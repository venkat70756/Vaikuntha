package com.adobe.aem.Vaikuntha.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.resourceTypes=Vaikuntha/components/content/demo",
        "sling.servlet.methods="+ HttpConstants.METHOD_GET,
        "sling.servlet.methods="+ HttpConstants.METHOD_POST,
        "sling.servlet.selectors=khan",
        "sling.servlet.extension="+ ".html"
})
public class PracticeResServlet extends SlingAllMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(PracticeResServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        log.info("PracticeResServlet doGet");
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException{
        log.info("PracticeResServlet doPost");
    }
}

//https://dummy.restapiexample.com/
