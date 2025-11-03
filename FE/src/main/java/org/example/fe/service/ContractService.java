package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.ContractResponse;

public interface ContractService {
    // Dùng Object để không bị lệch với BE (BE trả ApiResponse<Contract>)
    ApiResponse<Object> uploadContractImage(Integer transactionId, String contractImageUrl);
    ApiResponse<ContractResponse> getContractByTransactionId(Integer transactionId);
}