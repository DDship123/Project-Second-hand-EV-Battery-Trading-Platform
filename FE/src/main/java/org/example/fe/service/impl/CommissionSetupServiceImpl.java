package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.CommissionSetupResponse;
import org.example.fe.service.CommissionSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommissionSetupServiceImpl implements CommissionSetupService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8001";


    @Override
    public ApiResponse<List<CommissionSetupResponse>>  getAllCommissionSetups() {
        ApiResponse<List<CommissionSetupResponse>>  response = new  ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            // Implementation to call backend API and retrieve all commission setups
            ResponseEntity<ApiResponse<List<CommissionSetupResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/commission-setup/all",
                    org.springframework.http.HttpMethod.GET,
                    requestEntity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<List<CommissionSetupResponse>>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                response.error(apiResponse.getBody().getError());
            }
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Lỗi hệ thống: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }

    @Override
    public ApiResponse<CommissionSetupResponse>  getCommissionSetupById(Long id) {
        ApiResponse<CommissionSetupResponse> response = new  ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
            // Implementation to call backend API and retrieve commission setup by ID
            ResponseEntity<ApiResponse<CommissionSetupResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/commission-setup/" + id,
                    org.springframework.http.HttpMethod.GET,
                    requestEntity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<CommissionSetupResponse>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                response.error(apiResponse.getBody().getError());
            }
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Lỗi hệ thống: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }

    @Override
    public ApiResponse<CommissionSetupResponse>  saveCommissionSetup(CommissionSetupResponse commissionSetup) {
        ApiResponse<CommissionSetupResponse> response = new  ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<CommissionSetupResponse> requestEntity = new HttpEntity<>(commissionSetup, headers);
            // Implementation to call backend API and save commission setup
            ResponseEntity<ApiResponse<CommissionSetupResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/commission-setup/create",
                    org.springframework.http.HttpMethod.POST,
                    requestEntity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<CommissionSetupResponse>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                response.error(apiResponse.getBody().getError());
            }
        }catch (Exception e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Lỗi hệ thống: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
}
