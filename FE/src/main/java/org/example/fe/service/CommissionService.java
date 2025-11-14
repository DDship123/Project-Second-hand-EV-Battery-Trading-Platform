package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.CommissionResponse;


public interface CommissionService {
    public ApiResponse<CommissionResponse> getCommissionByTransactionId(int transactionId);

    public ApiResponse<Double> getTotalCommission();
}
