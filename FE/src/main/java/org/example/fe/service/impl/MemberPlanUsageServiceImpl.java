package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberPlanUsageResponse;
import org.example.fe.service.MemberPlanUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
public class MemberPlanUsageServiceImpl implements MemberPlanUsageService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<MemberPlanUsageResponse> getMemberPlanUsageByMemberId(Integer memberId) {
        ApiResponse<MemberPlanUsageResponse> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            // Simulate REST call to backend service
            ResponseEntity<ApiResponse<MemberPlanUsageResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/member-plan-usages/member/" + memberId,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<MemberPlanUsageResponse>>() {}
            );
            if (apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "Member plan usage not found");
                response.error(error);
            }
        }catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Failed to fetch member plan usage");
            response.error(error);
        }
        return response;
    }

    @Override
    public ApiResponse<MemberPlanUsageResponse> registerPackage(Integer memberId, Integer planId) {
        ApiResponse<MemberPlanUsageResponse> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            // Simulate REST call to backend service
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/member-plan-usages/register-package")
                    .queryParam("memberId", memberId)
                    .queryParam("planId", planId);
            ResponseEntity<ApiResponse<MemberPlanUsageResponse>> apiResponse = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.PUT,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<MemberPlanUsageResponse>>() {}
            );
            if (apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "Failed to register package");
                response.error(error);
            }
        }catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Failed to register package");
            response.error(error);
        }
        return response;
    }

    @Override
    public ApiResponse<Double> getTotalRevenue() {
        ApiResponse<Double> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            // Simulate REST call to backend service
            ResponseEntity<ApiResponse<Double>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/member-plan-usages/admin/total-revenue",
                    HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<Double>>() {}
            );
            if (apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "Failed to retrieve total revenue");
                response.error(error);
            }
        }catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Failed to get total revenue");
            response.error(error);
        }
        return response;
    }
}
