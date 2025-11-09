package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    ApiResponse<List<TransactionResponse>> getAllBuyTransaction(int memberId);
    ApiResponse<List<TransactionResponse>> getAllSellTransaction(int memberId);
//    ApiResponse<List<TransactionResponse>> getAllByStatus(String status);
    ApiResponse<TransactionResponse> update(Integer transactionId,String status);
    ApiResponse<List<TransactionResponse>> getAllBuyTransaction(int memberId ,String status);
    ApiResponse<List<TransactionResponse>> getAllSellTransaction(int memberId ,String status);

    ApiResponse<List<TransactionResponse>> getAllTransactions();

    ApiResponse<List<TransactionResponse>> getTransactionsForDashboard();

    ApiResponse<List<TransactionResponse>> getTransactionsByStatus(String status);

    ApiResponse<TransactionResponse> getTransactionById(Integer transactionId);

    ApiResponse<TransactionResponse> createTransaction(int buyerId, int postId);

    ApiResponse<TransactionResponse> updateTransactionStatus(Integer transactionId, String status);
}
