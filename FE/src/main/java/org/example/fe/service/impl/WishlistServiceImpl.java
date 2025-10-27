package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.FavoriteResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.service.WishlistService;
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
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";
    @Override
    public ApiResponse<FavoriteResponse> getLatest(MemberResponse member) {
        ApiResponse<FavoriteResponse> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();


        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<MemberResponse> requestEntity = new HttpEntity<>(member, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<FavoriteResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/wishlist//latest",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<FavoriteResponse>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get latest wishlist item successful
                response.ok(apiResponse.getBody().getPayload());
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
            ResponseEntity<ApiResponse<List<FavoriteResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/favorites/all/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<FavoriteResponse>>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get all wishlist items successful
                response.ok((List<FavoriteResponse>) apiResponse.getBody().getPayload());
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

    @Override
    public ApiResponse<FavoriteResponse> addWishlist(int memberId, int postId) {
        ApiResponse<FavoriteResponse> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();
//
//        // Validate input
//        if (memberId <= 0) {
//            errs.put("memberId", "Invalid member ID");
//            response.error(errs);
//            return response;
//        }
//
//        if (postId <= 0) {
//            errs.put("postId", "Invalid post ID");
//            response.error(errs);
//            return response;
//        }

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request body
            Map<String, Integer> requestBody = new HashMap<>();
            requestBody.put("memberId", memberId);
            requestBody.put("postId", postId);

            // Create request entity
            HttpEntity<Map<String, Integer>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<FavoriteResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/favorites",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<FavoriteResponse>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Add to wishlist successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Add to wishlist failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to add item to wishlist");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to add to wishlist: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<Boolean> deleteWishlist(int favoriteId) {
        ApiResponse<Boolean> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            // URL: /api/wishlist/{memberId}/{postId}
            ResponseEntity<ApiResponse<Boolean>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/favorites/" + favoriteId,
                    HttpMethod.DELETE,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<Boolean>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                // Delete from wishlist successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Delete from wishlist failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to delete item from wishlist");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to delete from wishlist: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<FavoriteResponse> getFist(int memberId) {
        ApiResponse<FavoriteResponse> response = new ApiResponse<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ApiResponse<FavoriteResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/favorites/first/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<FavoriteResponse>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            }else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "No items found in wishlist");
                response.error(errorMap);
            }
        }catch (Exception e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get first wishlist item: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
}
