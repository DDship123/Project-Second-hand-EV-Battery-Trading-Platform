package org.example.be.service;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> getTransactionById(Integer id);
    List<Transaction> getAllTransactions();
    Transaction updateTransaction(Integer id, Transaction updatedTransaction);
    boolean deleteTransaction(Integer id);

    List<Transaction> getAllBuyTransactions(Integer buyerId);
    List<Transaction> getAllSellTransactions(Integer sellerId);

    List<Transaction> getAllTransactionsByStatus(String status);
    List<Transaction> getTransactionsForDashboard();
    List<Transaction> getAllTransactionsByStatuses(java.util.List<String> statuses);
    List<Transaction> getAllBuyTransactionsByStatus(Integer buyerId, String status);
    List<Transaction> getAllSellTransactionsByStatus(Integer sellerId, String status);

    List<Transaction> findOtherTransactionsWithPostId(Integer postId, Integer transactionId);

    ApiResponse<Transaction> updateTransactionStatus(Integer transactionId, String status);

    List<Transaction> getTransactionsByPostId(Integer postId);
}