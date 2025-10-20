package org.example.fe.service.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<MemberResponse> signIn(String userName, String password) {

        ApiResponse<MemberResponse> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("username", userName);
            requestBody.put("password", password);

            // Create request entity
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<MemberResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/auth/login",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {
                    }
            );

            if (apiResponse.getBody().getStatus().equals("SUCCESS") && apiResponse.getBody() != null) {
                // Authentication successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Authentication failed
                Map<String, String> errorMap = apiResponse.getBody().getError();
                if (errorMap == null) {
                    errorMap = new HashMap<>();
                    errorMap.put("message", "Authentication failed");
                }
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Authentication failed: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<MemberResponse> register(MemberResponse registerMember) {

        ApiResponse<MemberResponse> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<MemberResponse> requestEntity = new HttpEntity<>(registerMember, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<MemberResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/auth/register",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {
                    }
            );

            if (apiResponse.getBody().getStatus().equals("SUCCESS") && apiResponse.getBody() != null) {
                // Authentication successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Registration failed
//                Map<String, String> errorMap = new HashMap<>();
//                errorMap.put("message", "Registration failed");
//                response.error(errorMap);

                Map<String, String> errorMap = apiResponse.getBody().getError();
                if (errorMap == null) {
                    errorMap = new HashMap<>();
                    errorMap.put("message", "Registration failed");
                }
                response.error(errorMap);
            }
        } catch (HttpClientErrorException e) {
            //  Nếu backend trả lỗi 400 → parse JSON body
            try {
                String responseBody = e.getResponseBodyAsString();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);

                JsonNode errorNode = root.path("error");
                Map<String, String> errorMap = new HashMap<>();
                if (errorNode.isObject()) {
                    errorNode.fieldNames().forEachRemaining(
                            field -> errorMap.put(field, errorNode.get(field).asText())
                    );
                } else {
                    errorMap.put("message", "Registration failed");
                }

                response.error(errorMap);
            } catch (Exception parseEx) {
                errs.put("message", "Registration failed: " + e.getMessage());
                response.error(errs);
            }

        } catch (Exception e) {
            // Handle exceptions
//            Map<String, String> errorMap = new HashMap<>();
//            errorMap.put("message", "Registration failed: " + e.getMessage());
//            response.error(errorMap);

            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Registration failed: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<MemberResponse> getMemberInfo(int memberId) {

        ApiResponse<MemberResponse> response = new ApiResponse<>();
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
            ResponseEntity<ApiResponse<MemberResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/members/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {
                    }
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get member info successful
                response.ok(apiResponse.getBody().getPayload());
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get member info: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }
    @Override
    public ApiResponse<MemberResponse> updateMember(MemberResponse updatedMember) {
        ApiResponse<MemberResponse> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<MemberResponse> requestEntity = new HttpEntity<>(updatedMember, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<MemberResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/members/"+ updatedMember.getMemberId(),
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {}
            );

            if (apiResponse.getBody() != null && "SUCCESS".equals(apiResponse.getBody().getStatus())) {
                // Update successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Update failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to update member");
                response.error(errorMap);
            }
        } catch (HttpClientErrorException e) {
            //  Nếu backend trả lỗi 400 → parse JSON body
            try {
                String responseBody = e.getResponseBodyAsString();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseBody);

                JsonNode errorNode = root.path("error");
                Map<String, String> errorMap = new HashMap<>();
                if (errorNode.isObject()) {
                    errorNode.fieldNames().forEachRemaining(
                            field -> errorMap.put(field, errorNode.get(field).asText())
                    );
                } else {
                    errorMap.put("message", "Registration failed");
                }

                response.error(errorMap);
            } catch (Exception parseEx) {
                errs.put("message", "Registration failed: " + e.getMessage());
                response.error(errs);
            }

        }  catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Update failed: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

}

