package org.example.fe.service.impl;

import org.example.fe.entity.CommentResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.service.CommentService;
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
public class CommentServiceImpl implements CommentService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<List<CommentResponse>> getAllComments(int postID) {
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<CommentResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/comments/post/" + postID,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<CommentResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get comments successful
                response.ok((List<CommentResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get comments failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve comments");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get comments: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }


    @Override
    public ApiResponse<CommentResponse> createComment(CommentResponse com) {
        ApiResponse<CommentResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<CommentResponse> requestEntity = new HttpEntity<>(com, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<CommentResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/comments",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<CommentResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Create comment successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Create comment failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to create comment");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to create comment: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }
}
