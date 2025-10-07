package org.example.be.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer reviewsId;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;      // FK

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Member reviewer;    // FK

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction; // FK

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment", length = 1000, columnDefinition = "NVARCHAR(1000)")
    private String comment;

    @Column(name = "status", length = 20, columnDefinition = "NVARCHAR(20)")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Integer getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(Integer reviewsId) {
        this.reviewsId = reviewsId;
    }

    public Member getSeller() {
        return seller;
    }

    public void setSeller(Member seller) {
        this.seller = seller;
    }

    public Member getReviewer() {
        return reviewer;
    }

    public void setReviewer(Member reviewer) {
        this.reviewer = reviewer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
