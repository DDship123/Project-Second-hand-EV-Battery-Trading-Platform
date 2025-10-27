package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.ReviewResponse;
import org.example.fe.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<List<ReviewResponse>> getAllBuyerReview(int postID) {
        ApiResponse<List<ReviewResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<ReviewResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/reviews/buyer/" + postID,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<ReviewResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get buyer reviews successful
                response.ok((List<ReviewResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get buyer reviews failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve buyer reviews");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get buyer reviews: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<ReviewResponse>> FindAllReviewBySellerId(int sellerId) {
        ApiResponse<List<ReviewResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend with sellerId parameter
            ResponseEntity<ApiResponse<List<ReviewResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/reviews/seller/" + sellerId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<ReviewResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get reviews by sellerId successful
                response.ok((List<ReviewResponse>) apiResponse.getBody().getPayload());
            } else {
                // Get reviews by sellerId failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve reviews for seller: " + sellerId);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get reviews by sellerId: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<ReviewResponse>> findAllReviewByStatus(String status) {
        ApiResponse<List<ReviewResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<ReviewResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/reviews/status/" + status,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<ReviewResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                //  successful
                response.ok((List<ReviewResponse>) apiResponse.getBody().getPayload());
            } else {
                //  failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve reviews for status: " + status);
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get reviews by status: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<ReviewResponse> update(ReviewResponse review) {
        ApiResponse<ReviewResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<ReviewResponse> requestEntity = new HttpEntity<>(review, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<ReviewResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/reviews/update-status",
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<ReviewResponse>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                //  successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Get reviews by sellerId failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to update review for review id: " + review.getReviewId());
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to update review for review id : " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

}
