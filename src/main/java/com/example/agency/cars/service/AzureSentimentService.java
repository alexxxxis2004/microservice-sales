package com.example.agency.cars.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AzureSentimentService {

    @Value("${azure.ai.endpoint}")
    private String endpoint;

    @Value("${azure.ai.key}")
    private String apiKey;

    @Value("${azure.ai.api-version}")
    private String apiVersion;

    public JsonNode analyze(String text) {
        try {
            String url = endpoint + "language/:analyze-text?api-version=" + apiVersion;

            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Ocp-Apim-Subscription-Key", apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Body request
            String body = """
            {
              "kind": "SentimentAnalysis",
              "analysisInput": {
                "documents": [
                  {
                    "id": "1",
                    "language": "es",
                    "text": "%s"
                  }
                ]
              }
            }
            """.formatted(text);

            HttpEntity<String> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = rest.postForEntity(url, request, String.class);

            return new ObjectMapper().readTree(response.getBody());

        } catch (Exception e) {
            throw new RuntimeException("Error calling Azure Cognitive Services", e);
        }
    }
}
