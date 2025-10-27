package org.example.fe.service.impl;

import org.example.fe.request.CreatePaymentRequest;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.PaymentUrlResponse;
import org.example.fe.response.ReturnUrlResponse;
import org.example.fe.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<PaymentUrlResponse> createBanking(CreatePaymentRequest req) {
        ApiResponse<PaymentUrlResponse> response = new ApiResponse<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<CreatePaymentRequest> requestEntity = new HttpEntity<>(req, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<PaymentUrlResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/v1/payments/vnpay/create",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<PaymentUrlResponse>>(){}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Create payment successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Create payment failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to create payment");
                response.error(errorMap);
            }
        }catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get comments: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }

    @Override
    public ApiResponse<ReturnUrlResponse> handleReturnUrlMultiParam(String returnUrl) {
        ApiResponse<ReturnUrlResponse> response = new ApiResponse<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<ReturnUrlResponse>> apiResponse = restTemplate.exchange(
                    returnUrl,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<ReturnUrlResponse>>(){}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Handle return URL successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Handle return URL failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to handle return URL");
                response.error(errorMap);
            }
        }catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to handle return URL: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
}
