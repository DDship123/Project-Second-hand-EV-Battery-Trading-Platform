package org.example.be.dto;

import java.time.LocalDateTime;

public class TransactionResponse {
    private Integer transactionId;
    private Integer buyerId;
    private String buyerName;
    private Integer sellerId;
    private String sellerName;
    private Integer postId;
    private String postTitle;
    private String status;
    private LocalDateTime createdAt;

    public TransactionResponse(Integer transactionId, Integer buyerId, String buyerName,
                               Integer sellerId, String sellerName,
                               Integer postId, String postTitle,
                               String status, LocalDateTime createdAt) {
        this.transactionId = transactionId;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.postId = postId;
        this.postTitle = postTitle;
        this.status = status;
        this.createdAt = createdAt;
    }

    // getters, setters
}
