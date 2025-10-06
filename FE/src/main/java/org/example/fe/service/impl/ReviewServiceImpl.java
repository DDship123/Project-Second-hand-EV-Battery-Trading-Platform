package org.example.fe.service.impl;

import org.example.fe.entity.ReviewResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.service.ReviewService;
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
                response.ok((List<ReviewResponse>) apiResponse.getBody());
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

}
