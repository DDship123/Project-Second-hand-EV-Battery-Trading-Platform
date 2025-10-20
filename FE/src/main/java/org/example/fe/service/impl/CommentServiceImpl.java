package org.example.fe.service.impl;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.CommentResponse;
import org.example.fe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("postId", String.valueOf(com.getPost().getPostsId()));
            formData.add("memberId", String.valueOf(com.getMember().getMemberId()));
            formData.add("comment", com.getComment());
            formData.add("rating", String.valueOf(com.getRating()));
            formData.add("status", com.getStatus());

            // Create request entity
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

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

    @Override
    public ApiResponse<List<CommentResponse>> FindAllCommentByPostId(int postId) {
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend with postId parameter
            ResponseEntity<ApiResponse<List<CommentResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/comments/post/" + postId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<CommentResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get comments by postId successful
                response.ok((List<CommentResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get comments by postId failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve comments for post: " + postId);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get comments by postId: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<CommentResponse>> findAllCommentByStatus(String status) {
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend with status parameter
            ResponseEntity<ApiResponse<List<CommentResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/comments/status/" + status,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<CommentResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get comments by status successful
                response.ok((List<CommentResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get comments by status failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve comments with status: " + status);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get comments by status: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<CommentResponse> updateCommentStatus(int commentId, String status) {
        ApiResponse<CommentResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("status", status);

            // Create request entity
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<CommentResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/comments/update/" + commentId,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<CommentResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Update comment status successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Update comment status failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to update comment status");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to update comment status: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }
}
