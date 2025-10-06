package org.example.fe.service.impl;

import org.example.fe.entity.PostResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";
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
            ResponseEntity<ApiResponse<List<PostResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/top-rated",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<PostResponse>>>(){}
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

    @Override
    public ApiResponse<List<PostResponse>> getAllPostBatery() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<List> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/battery",
                    HttpMethod.GET,
                    requestEntity,
                    List.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get battery posts successful
                response.ok((List<PostResponse>) apiResponse.getBody());
            } else {
                // Get battery posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve battery posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get battery posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<PostResponse>> getAllPostVehicle() {
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<List> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/vehicle",
                    HttpMethod.GET,
                    requestEntity,
                    List.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get vehicle posts successful
                response.ok((List<PostResponse>) apiResponse.getBody());
            } else {
                // Get vehicle posts failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve vehicle posts");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get vehicle posts: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<PostResponse> getPostDetail(int postID) {
        ApiResponse<PostResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<PostResponse> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/posts/" + postID,
                    HttpMethod.GET,
                    requestEntity,
                    PostResponse.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get post detail successful
                response.ok(apiResponse.getBody());
            } else {
                // Get post detail failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve post detail");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get post detail: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }
}
