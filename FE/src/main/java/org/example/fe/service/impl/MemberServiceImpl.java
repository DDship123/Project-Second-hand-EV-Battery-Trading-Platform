package org.example.fe.service.impl;


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
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8001";

    @Override
    public ApiResponse<MemberResponse> signIn(String userName, String password) {

        ApiResponse<MemberResponse> response = new ApiResponse<>();
        Map<String,String> errs = new HashMap<>();
//        //validate input
//        if(!StringUtils.hasText(userName)){     //check emty, blank username
//            errs.put("username","Username cannot be empty");
//        }
//        if(!StringUtils.hasText(password)){     //check emty, blank password
//            errs.put("password","Password cannot be empty");
//        }
//        if(!errs.isEmpty()){
//            response.error(errs);
//            return response;
//        }

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
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {}
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

//        // Validate input
//        if (!StringUtils.hasText(memberRegister.getUsername())) {
//            errs.put("username", "Username cannot be empty");
//        }
//        if (!StringUtils.hasText(memberRegister.getPassword())) {
//            errs.put("password", "Password cannot be empty");
//        }
//        if (!StringUtils.hasText(memberRegister.getEmail())) {
//            errs.put("email", "Email cannot be empty");
//        }
//        if (!StringUtils.hasText(memberRegister.getPhoneNumber())) {
//            errs.put("phoneNumber", "Phone number cannot be empty");
//        }
//        if (!StringUtils.hasText(memberRegister.getAddress())) {
//            errs.put("phoneNumber", "Address cannot be empty");
//        }
//
//        if (!errs.isEmpty()) {
//            response.error(errs);
//            return response;
//        }

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
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Registration successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Registration failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Registration failed");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
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
                    new ParameterizedTypeReference<ApiResponse<MemberResponse>>() {}
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

//        // Validate input
//        if (!StringUtils.hasText(memberUpdate.getUsername())) {
//            errs.put("username", "Username cannot be empty");
//        }
//        if (!StringUtils.hasText(memberUpdate.getEmail())) {
//            errs.put("email", "Email cannot be empty");
//        }
//
//        if (!errs.isEmpty()) {
//            response.error(errs);
//            return response;
//        }

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

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Update successful
                response.ok(apiResponse.getBody().getPayload());
            } else {
                // Update failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to update member");
                response.error(errorMap);
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Update failed: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }
}
