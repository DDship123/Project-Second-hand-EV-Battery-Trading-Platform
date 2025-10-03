package org.example.fe.service.impl;

import org.example.fe.entity.FavoriteResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.service.WishlistService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishlistServiceImpl implements WishlistService {
    private String apiBaseUrl;
    private RestTemplate restTemplate;
    @Override
    public ApiResponse<FavoriteResponse> getLatest(int memberId) {
        ApiResponse<FavoriteResponse> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();

        // Validate input
        if (memberId <= 0) {
            errs.put("memberId", "Invalid member ID");
            response.error(errs);
            return response;
        }

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<FavoriteResponse> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/wishlist/" + memberId + "/latest",
                    HttpMethod.GET,
                    requestEntity,
                    FavoriteResponse.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get latest wishlist item successful
                response.ok(apiResponse.getBody());
            } else {
                // Get latest wishlist item failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "No items found in wishlist");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get latest wishlist item: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<FavoriteResponse>> getAll(int memberId) {
        ApiResponse<List<FavoriteResponse>> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();

        // Validate input
        if (memberId <= 0) {
            errs.put("memberId", "Invalid member ID");
            response.error(errs);
            return response;
        }

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<List> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/wishlist/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    List.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get all wishlist items successful
                response.ok((List<FavoriteResponse>) apiResponse.getBody());
            } else {
                // Get wishlist items failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve wishlist items");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get wishlist: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }
}
