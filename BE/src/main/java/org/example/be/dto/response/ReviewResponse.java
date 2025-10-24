package org.example.be.dto.response;

import java.time.LocalDateTime;

public class ReviewResponse {
    private int reviewId;
    private MemberResponse seller;
    private MemberResponse reviewer;
    private TransactionResponse transaction;
    private int rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;

    public ReviewResponse() {
    }

    public ReviewResponse(int reviewId, MemberResponse seller, MemberResponse reviewer, TransactionResponse transaction, int rating, String comment, String status, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.seller = seller;
        this.reviewer = reviewer;
        this.transaction = transaction;
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

    public MemberResponse getSeller() {
        return seller;
    }

    public void setSeller(MemberResponse seller) {
        this.seller = seller;
    }

    public MemberResponse getReviewer() {
        return reviewer;
    }

    public void setReviewer(MemberResponse reviewer) {
        this.reviewer = reviewer;
    }

    public TransactionResponse getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionResponse transaction) {
        this.transaction = transaction;
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
