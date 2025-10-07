package org.example.be.dto.reponse;

import java.time.LocalDateTime;

public class ReviewResponse {
    private int reviewId;
    private int sellerId;
    private int reviewerId;
    private int transactionId;
    private int rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;

    public ReviewResponse() {
    }

    public ReviewResponse(int reviewId, int sellerId, int reviewerId, int transactionId, int rating, String comment, String status, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.sellerId = sellerId;
        this.reviewerId = reviewerId;
        this.transactionId = transactionId;
        this.rating = rating;
        this.comment = comment;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
