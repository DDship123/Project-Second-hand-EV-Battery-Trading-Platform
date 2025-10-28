package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.ContractResponse;

public interface ContractService {
    public ApiResponse<ContractResponse> uploadContractImage(Integer transactionId, String contractImageUrl);
}
