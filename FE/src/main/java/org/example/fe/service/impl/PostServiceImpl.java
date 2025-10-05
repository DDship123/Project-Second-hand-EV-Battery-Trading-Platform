package org.example.fe.service.impl;

import org.example.fe.entity.PostResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.service.PostService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostServiceImpl implements PostService {
    private String apiBaseUrl;
    private RestTemplate restTemplate;
    @Override
    public ApiResponse<List<PostResponse>> getAllPost() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<List> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/top-rated",
                    HttpMethod.GET,
                    requestEntity,
                    List.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get posts successful
                response.ok((List<PostResponse>) apiResponse.getBody());
            } else {
                // Get posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<PostResponse>> getLatestPost() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<List> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/latest",
                    HttpMethod.GET,
                    requestEntity,
                    List.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get latest posts successful
                response.ok((List<PostResponse>) apiResponse.getBody());
            } else {
                // Get latest posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve latest posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get latest posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }
}
