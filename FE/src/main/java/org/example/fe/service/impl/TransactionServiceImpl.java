package org.example.fe.service.impl;

import org.example.fe.entity.TransactionResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.service.TransactionService;
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
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";
    @Override
    public ApiResponse<List<TransactionResponse>> getAllBuyTransaction(int memberId) {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();

//        // Validate input
//        if (memberId <= 0) {
//            errs.put("memberId", "Invalid member ID");
//            response.error(errs);
//            return response;
//        }

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions/buy/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get buy transactions successful
                response.ok((List<TransactionResponse>) apiResponse.getBody());
            } else {
                // Get buy transactions failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve buy transactions");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get buy transactions: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<List<TransactionResponse>> getAllSellTransaction(int memberId) {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();

//        // Validate input
//        if (memberId <= 0) {
//            errs.put("memberId", "Invalid member ID");
//            response.error(errs);
//            return response;
//        }

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions/sell/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get sell transactions successful
                response.ok((List<TransactionResponse>) apiResponse.getBody());
            } else {
                // Get sell transactions failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve sell transactions");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get sell transactions: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }
}
