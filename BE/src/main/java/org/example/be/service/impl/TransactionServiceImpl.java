package org.example.be.service.impl;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.Transaction;
import org.example.be.repository.TransactionRepository;
import org.example.be.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> requestedTransactions = new ArrayList<>();
        List<Transaction> delivery= transactionRepository.findAllByStatusOrderByCreatedAtDesc("DELIVERED");
        List<Transaction> paid = transactionRepository.findAllByStatusOrderByCreatedAtDesc("PAID");
        List<Transaction> accept = transactionRepository.findAllByStatusOrderByCreatedAtDesc("ACCEPTED");
        List<Transaction> request = transactionRepository.findAllByStatusOrderByCreatedAtDesc("REQUESTED");
        requestedTransactions.addAll(delivery);
        requestedTransactions.addAll(paid);
        requestedTransactions.addAll(accept);
        requestedTransactions.addAll(request);
        return requestedTransactions;
    }

    @Override
    public Transaction updateTransaction(Integer id, Transaction updatedTransaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction transaction = existingTransaction.get();
            transaction.setBuyer(updatedTransaction.getBuyer());
            transaction.setPost(updatedTransaction.getPost());
            transaction.setStatus(updatedTransaction.getStatus());
            transaction.setCreatedAt(updatedTransaction.getCreatedAt());
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public boolean deleteTransaction(Integer id) {
        Optional<Transaction> existing = transactionRepository.findById(id);
        if (existing.isPresent()) {
            transactionRepository.delete(existing.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Transaction> getAllBuyTransactions(Integer buyerId) {
        return transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, "COMPLETED");
    }

    @Override
    public List<Transaction> getAllSellTransactions(Integer sellerId) {
        return transactionRepository.findBySeller_MemberIdAndStatus(sellerId, "COMPLETED");
    }

    @Override
    public List<Transaction> getAllTransactionsByStatus(String status) {
        if (status.equals("ALL"))
        {
            List<Transaction> result = new ArrayList<>();
            List<Transaction> delivered = transactionRepository.findAllByStatusOrderByCreatedAtDesc("DELIVERED");
            List<Transaction> paid = transactionRepository.findAllByStatusOrderByCreatedAtDesc("PAID");
            List<Transaction> accepted = transactionRepository.findAllByStatusOrderByCreatedAtDesc("ACCEPTED");
            List<Transaction> requested = transactionRepository.findAllByStatusOrderByCreatedAtDesc("REQUESTED");
            result.addAll(delivered);
            result.addAll(requested);
            result.addAll(accepted);
            result.addAll(paid);
            return result;
        }
        return transactionRepository.findAllByStatusOrderByCreatedAtDesc(status);
    }

    @Override
    public List<Transaction> getTransactionsForDashboard() {
        return transactionRepository.findAllByStatusOrderByCreatedAtDesc("REQUESTED");
    }

    @Override
    public List<Transaction> getAllTransactionsByStatuses(java.util.List<String> statuses) {
        return transactionRepository.findAllByStatusInOrderByCreatedAtDesc(statuses);
    }

    @Override
    public List<Transaction> getAllBuyTransactionsByStatus(Integer buyerId, String status) {
        if (status.equals("C-C-ALL"))
        {
            List<Transaction> result = new ArrayList<>();
            List<Transaction> completed = transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, "COMPLETED");
            List<Transaction> cancelled = transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, "CANCELLED");
            result.addAll(completed);
            result.addAll(cancelled);
            return result;
        } else if (!status.equals("REQUESTED") && !status.equals("CANCELLED") && !status.equals("COMPLETED")) {
            List<Transaction> result = new ArrayList<>();
            List<Transaction> delivered = transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, "DELIVERED");
            List<Transaction> paid = transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, "PAID");
            List<Transaction> accepted = transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, "ACCEPTED");
            result.addAll(accepted);
            result.addAll(paid);
            result.addAll(delivered);
            return result;
        }
        return transactionRepository.findByBuyer_MemberIdAndStatus(buyerId, status);
    }

    @Override
    public List<Transaction> getAllSellTransactionsByStatus(Integer sellerId, String status) {
        if (status.equals("C-C-ALL"))
        {
            List<Transaction> result = new ArrayList<>();
            List<Transaction> completed = transactionRepository.findByPost_Seller_MemberIdAndStatus(sellerId, "COMPLETED");
            List<Transaction> cancelled = transactionRepository.findByPost_Seller_MemberIdAndStatus(sellerId, "CANCELLED");
            result.addAll(completed);
            result.addAll(cancelled);
            return result;
        } else if (!status.equals("REQUESTED") && !status.equals("CANCELLED") && !status.equals("COMPLETED")) {
            List<Transaction> result = new ArrayList<>();
            List<Transaction> delivered = transactionRepository.findByPost_Seller_MemberIdAndStatus(sellerId, "DELIVERED");
            List<Transaction> paid = transactionRepository.findByPost_Seller_MemberIdAndStatus(sellerId, "PAID");
            List<Transaction> accepted = transactionRepository.findByPost_Seller_MemberIdAndStatus(sellerId, "ACCEPTED");
            result.addAll(accepted);
            result.addAll(paid);
            result.addAll(delivered);
            return result;
        }
        return transactionRepository.findByPost_Seller_MemberIdAndStatus(sellerId, status);
    }

    @Override
    public List<Transaction> findOtherTransactionsWithPostId(Integer postId, Integer transactionId) {
        return transactionRepository.findOthersWithPostId(postId, transactionId);
    }

    @Override
    public ApiResponse<Transaction> updateTransactionStatus(Integer transactionId, String status) {
        ApiResponse<Transaction> response = new ApiResponse<>();
        try {
            Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
            if (existingTransaction.isEmpty()) {
                java.util.Map<String, String> error = new java.util.HashMap<>();
                error.put("message", "Transaction not found");
                response.error(error);
                return response;
            }

            Transaction transaction = existingTransaction.get();
            transaction.setStatus(status);
            Transaction updated = transactionRepository.save(transaction);

            response.ok(updated);
            return response;
        } catch (Exception e) {
            java.util.Map<String, String> error = new java.util.HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return response;
        }
    }

    @Override
    public List<Transaction> getTransactionsByPostId(Integer postId) {
        return transactionRepository.findByPost_PostsId(postId);
    }
}