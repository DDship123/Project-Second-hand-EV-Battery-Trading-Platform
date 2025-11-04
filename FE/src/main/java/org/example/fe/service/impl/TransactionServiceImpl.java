package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.TransactionResponse;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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


        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions/buy/completed/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get buy transactions successful
                response.ok((List<TransactionResponse>) apiResponse.getBody().getPayload());
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
                    apiBaseUrl + "/api/transactions/sell/completed/" + memberId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get sell transactions successful
                response.ok((List<TransactionResponse>) apiResponse.getBody().getPayload());
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

    @Override
    public ApiResponse<List<TransactionResponse>> getAllByStatus(String status) {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Build URL with query parameter
            String url = apiBaseUrl + "/api/transactions?status=" + status;

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                ApiResponse<List<TransactionResponse>> body = apiResponse.getBody();

                if ("SUCCESS".equals(body.getStatus())) {
                    // Get transactions by status successful
                    response.ok(body.getPayload());
                } else {
                    // API call thành công nhưng backend trả về lỗi
                    Map<String, String> errorMap = body.getError();
                    if (errorMap == null || errorMap.isEmpty()) {
                        errorMap = new HashMap<>();
                        errorMap.put("message", "Failed to get transactions by status");
                    }
                    response.error(errorMap);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get transactions by status: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<TransactionResponse> update(Integer transactionId,String status) {
        ApiResponse<TransactionResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("transactionId", transactionId);
            requestBody.put("status", status);

            // Create request entity
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<TransactionResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions/" + transactionId + "/status",
                    HttpMethod.PATCH,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<TransactionResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                ApiResponse<TransactionResponse> body = apiResponse.getBody();

                if ("SUCCESS".equals(body.getStatus())) {
                    // Update transaction status successful
                    response.ok(body.getPayload());
                } else {
                    // API call thành công nhưng backend trả về lỗi
                    Map<String, String> errorMap = body.getError();
                    if (errorMap == null || errorMap.isEmpty()) {
                        errorMap = new HashMap<>();
                        errorMap.put("message", "Failed to update transaction status");
                    }
                    response.error(errorMap);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to update transaction status: " + e.getMessage());
            response.error(errorMap);
        }

        return response;

    }

    @Override
    public ApiResponse<List<TransactionResponse>> getAllBuyTransaction(int memberId, String status) {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/api/transactions/buy/status")
                    .queryParam("memberId", memberId)
                    .queryParam("status", status)
                    .toUriString();

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get buy transactions successful
                response.ok((List<TransactionResponse>) apiResponse.getBody().getPayload());
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
    public ApiResponse<List<TransactionResponse>> getAllSellTransaction(int memberId, String status) {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        Map<String, String> errs = new HashMap<>();
        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/api/transactions/sell/status")
                    .queryParam("memberId", memberId)
                    .queryParam("status", status)
                    .toUriString();

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                // Get buy transactions successful
                response.ok((List<TransactionResponse>) apiResponse.getBody().getPayload());
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
    public ApiResponse<List<TransactionResponse>> getAllTransactions() {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<List<TransactionResponse>>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<List<TransactionResponse>>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                ApiResponse<List<TransactionResponse>> body = apiResponse.getBody();

                if ("SUCCESS".equals(body.getStatus())) {
                    // Get all transactions successful
                    response.ok(body.getPayload());
                } else {
                    // API call thành công nhưng backend trả về lỗi
                    Map<String, String> errorMap = body.getError();
                    if (errorMap == null || errorMap.isEmpty()) {
                        errorMap = new HashMap<>();
                        errorMap.put("message", "Failed to get all transactions");
                    }
                    response.error(errorMap);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get all transactions: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<TransactionResponse> getTransactionById(Integer transactionId) {
        ApiResponse<TransactionResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create request entity
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<TransactionResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions/" + transactionId,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<TransactionResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                ApiResponse<TransactionResponse> body = apiResponse.getBody();

                if ("SUCCESS".equals(body.getStatus())) {
                    // Get transaction by ID successful
                    response.ok(body.getPayload());
                } else {
                    // API call thành công nhưng backend trả về lỗi
                    Map<String, String> errorMap = body.getError();
                    if (errorMap == null || errorMap.isEmpty()) {
                        errorMap = new HashMap<>();
                        errorMap.put("message", "Failed to get transaction by ID");
                    }
                    response.error(errorMap);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to get transaction by ID: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<TransactionResponse> createTransaction(int buyerId, int postId) {
        ApiResponse<TransactionResponse> response = new ApiResponse<>();

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Create request body
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("buyerId", String.valueOf(buyerId));
            formData.add("postId",  String.valueOf( postId));
            // Create request entity
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

            // Make API call to backend
            ResponseEntity<ApiResponse<TransactionResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<TransactionResponse>>(){}
            );

            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                ApiResponse<TransactionResponse> body = apiResponse.getBody();

                if ("SUCCESS".equals(body.getStatus())) {
                    // Create transaction successful
                    response.ok(body.getPayload());
                } else {
                    // API call thành công nhưng backend trả về lỗi
                    Map<String, String> errorMap = body.getError();
                    if (errorMap == null || errorMap.isEmpty()) {
                        errorMap = new HashMap<>();
                        errorMap.put("message", "Failed to create transaction");
                    }
                    response.error(errorMap);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to create transaction: " + e.getMessage());
            response.error(errorMap);
        }

        return response;
    }

    @Override
    public ApiResponse<TransactionResponse> updateTransactionStatus(Integer transactionId, String status) {
        ApiResponse<TransactionResponse> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("status", status);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
            ResponseEntity<ApiResponse<TransactionResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/transactions/update-status/" + transactionId,
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<TransactionResponse>>() {}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                ApiResponse<TransactionResponse> body = apiResponse.getBody();
                if ("SUCCESS".equals(body.getStatus())) {
                    response.ok(body.getPayload());
                } else {
                    Map<String, String> errorMap = body.getError();
                    if (errorMap == null || errorMap.isEmpty()) {
                        errorMap = new HashMap<>();
                        errorMap.put("message", "Failed to update transaction status");
                    }
                    response.error(errorMap);
                }
            }
        }catch (Exception e){
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to update transaction status: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
}
