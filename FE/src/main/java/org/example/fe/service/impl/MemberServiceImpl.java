package org.example.fe.service.impl;


import org.example.fe.entity.MemberResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.model.response.MemberRegister;
import org.example.fe.model.response.MemberUpdate;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RestTemplate restTemplate;
    private String apiBaseUrl = "http://localhost:8080";

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
            ResponseEntity<MemberResponse> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/auth/signin",
                    HttpMethod.POST,
                    requestEntity,
                    MemberResponse.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Authentication successful
                response.ok(apiResponse.getBody());
            } else {
                // Authentication failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Invalid username or password");
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
    public ApiResponse<MemberResponse> register(MemberRegister memberRegister) {

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
            HttpEntity<MemberRegister> requestEntity = new HttpEntity<>(memberRegister, headers);

            // Make API call to backend
            ResponseEntity<MemberResponse> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/auth/register",
                    HttpMethod.POST,
                    requestEntity,
                    MemberResponse.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Registration successful
                response.ok(apiResponse.getBody());
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
            ResponseEntity<MemberResponse> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/members/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    MemberResponse.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get member info successful
                response.ok(apiResponse.getBody());
            } else {
                // Get member info failed
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Member not found");
                response.error(errorMap);
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
    public ApiResponse<MemberResponse> updateMember(MemberUpdate memberUpdate) {
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
            HttpEntity<MemberUpdate> requestEntity = new HttpEntity<>(memberUpdate, headers);

            // Make API call to backend
            ResponseEntity<MemberResponse> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/members",
                    HttpMethod.PUT,
                    requestEntity,
                    MemberResponse.class
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Update successful
                response.ok(apiResponse.getBody());
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
