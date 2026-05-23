package com.adobe.aem.Vaikuntha.core.servlets;

import com.adobe.aem.Vaikuntha.core.services.DemoService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class,
        property = {
            "sling.servlet.paths=/bin/first",
             "sling.servlet.methods="+ HttpConstants.METHOD_GET,
        }
)
public class FirstServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(FirstServlet.class);

//    @Reference
//    DemoService demoService;

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response)
            throws IOException {

        log.info("First Servlet GET Method is working");

        String apiUrl = "https://api.restful-api.dev/objects";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader("Accept", "application/json");

            try (CloseableHttpResponse apiResponse = httpClient.execute(httpGet)) {

                int statusCode = apiResponse.getStatusLine().getStatusCode();
                log.info("API Response Code: {}", statusCode);

                HttpEntity entity = apiResponse.getEntity();
                String result = entity != null ? EntityUtils.toString(entity) : "";

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(result);
            }

        } catch (Exception e) {
            log.error("Error while calling external API", e);
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"Failed to fetch data\"}");
        }
    }

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException, ServletException{
        log.info("First Servlet Post method is working");
    }

}
