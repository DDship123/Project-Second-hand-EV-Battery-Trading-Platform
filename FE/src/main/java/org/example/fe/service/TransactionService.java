package org.example.fe.service;

import org.example.fe.entity.TransactionResponse;
import org.example.fe.model.response.ApiResponse;

import java.util.List;

public interface TransactionService {
    ApiResponse<List<TransactionResponse>> getAllBuyTransaction(int memberId);
    ApiResponse<List<TransactionResponse>> getAllSellTransaction(int memberId);

}
