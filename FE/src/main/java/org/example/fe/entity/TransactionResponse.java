package org.example.fe.entity;

import java.time.LocalDateTime;

public class TransactionResponse {
    private int transactionId;
    private int buyerId;
    private int sellerId;
    private int postId;
    private String status;
    private LocalDateTime createdAt;

    public TransactionResponse() {
    }

    public TransactionResponse(int transactionId, int buyerId, int sellerId, int postId, String status, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.postId = postId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
