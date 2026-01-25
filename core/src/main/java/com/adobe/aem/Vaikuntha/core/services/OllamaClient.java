package com.adobe.aem.Vaikuntha.core.services;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = OllamaClient.class)
public class OllamaClient {

    private static final Logger LOG =
            LoggerFactory.getLogger(OllamaClient.class);

    private static final String BASE_URL = "http://localhost:11434";
    private static final String MODEL = "llama3.2";

    public String generate(String prompt) throws Exception {

        LOG.debug("Generating response using model: {}", MODEL);

        String payload = "{"
                + "\"model\":\"" + MODEL + "\","
                + "\"prompt\":\"" + escapeJson(prompt) + "\","
                + "\"stream\":false"
                + "}";

        HttpPost post = new HttpPost(BASE_URL + "/api/generate");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(payload));

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String response = EntityUtils.toString(
                    client.execute(post).getEntity(),
                    "UTF-8"
            );
            LOG.debug("Ollama response successfully received");
            return response;
        }
    }

    private String escapeJson(String input) {
        return input == null ? "" : input.replace("\"", "\\\"");
    }
}
