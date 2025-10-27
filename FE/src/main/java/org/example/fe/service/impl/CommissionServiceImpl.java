package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.CommissionResponse;
import org.example.fe.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommissionServiceImpl implements CommissionService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<CommissionResponse> getCommissionByTransactionId(int transactionId) {
        ApiResponse<CommissionResponse> response = new  ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<ApiResponse<CommissionResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/commissions/transaction/" + transactionId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<CommissionResponse>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to retrieve commission");
                response.error(errorMap);
            }

        }catch (Exception e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get commission: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
}
