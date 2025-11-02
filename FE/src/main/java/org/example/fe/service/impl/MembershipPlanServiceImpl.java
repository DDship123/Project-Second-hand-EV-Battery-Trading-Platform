package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.MembershipPlanResponse;
import org.example.fe.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class MembershipPlanServiceImpl implements MembershipPlanService {
    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<MembershipPlanResponse> getMembershipPlanByMemberId(Integer memberId) {
        ApiResponse<MembershipPlanResponse> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<ApiResponse<MembershipPlanResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl+"/api/membership-plans/member/" + memberId,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<ApiResponse<MembershipPlanResponse>>() {}
            );
            if (apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "Membership plan not found");
                response.error(error);
            }

        }catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Failed to fetch membership plan");
            response.error(error);
        }
        return response;
    }

    @Override
    public ApiResponse<List<MembershipPlanResponse>> getAllMembershipPlans() {
        ApiResponse<List<MembershipPlanResponse>> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<ApiResponse<List<MembershipPlanResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl+"/api/membership-plans",
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<ApiResponse<List<MembershipPlanResponse>>>() {}
            );
            if (apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "No membership plans found");
                response.error(error);
            }
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Failed to fetch membership plans");
            response.error(error);
        }
        return response;
    }
}
