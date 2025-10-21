package org.example.fe.service;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.TransactionResponse;

import java.util.List;

public interface TransactionService {
    ApiResponse<List<TransactionResponse>> getAllBuyTransaction(int memberId);
    ApiResponse<List<TransactionResponse>> getAllSellTransaction(int memberId);
    ApiResponse<List<TransactionResponse>> getAllByStatus(String status);
    ApiResponse<TransactionResponse> update(Integer transactionId,String status);
}
