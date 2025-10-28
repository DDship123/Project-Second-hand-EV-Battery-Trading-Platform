package org.example.fe.service.impl;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.CommentResponse;
import org.example.fe.response.ContractResponse;
import org.example.fe.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private RestTemplate restTemplate;

    private String apiBaseUrl = "http://localhost:8001";


    @Override
    public ApiResponse<ContractResponse> uploadContractImage(Integer transactionId, String contractImageUrl) {
        ApiResponse<ContractResponse> response = new  ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("transactionId", transactionId.toString());
            formData.add("contractImageUrl", contractImageUrl);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

            ResponseEntity<ApiResponse<ContractResponse>> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/contracts/upload",
                    HttpMethod.PUT,
                    requestEntity,
                    new ParameterizedTypeReference<ApiResponse<ContractResponse>>(){}
            );
            if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
                response.ok(apiResponse.getBody().getPayload());
            } else {
                HashMap<String, String> errorMap = new HashMap<>();
                errorMap.put("message", "Failed to upload contract image");
                response.error(errorMap);
            }
        }catch (Exception e){
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("message", "Failed to upload contract image: " + e.getMessage());
            response.error(errorMap);
        }
        return response;
    }
}
