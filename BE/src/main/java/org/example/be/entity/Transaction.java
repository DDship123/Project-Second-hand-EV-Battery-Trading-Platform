package org.example.be.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactions_id")
    private Integer transactionsId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Member buyer;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(columnDefinition = "NVARCHAR(20)")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Integer getTransactionsId() {
        return transactionsId;
    }

    public void setTransactionsId(Integer transactionsId) {
        this.transactionsId = transactionsId;
    }

    public Member getBuyer() {
        return buyer;
    }

    public void setBuyer(Member buyer) {
        this.buyer = buyer;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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