package org.example.fe.entity;

import java.time.LocalDateTime;

public class PostResponse {
    private int postId;
    private int productId;
    private int memberId;
    private String title;
    private String description;
    private String status;
    private double price;
    private LocalDateTime createdAt;

    public PostResponse() {
    }

    public PostResponse(int postId, int productId, int memberId, String title, String description, String status, double price, LocalDateTime createdAt) {
        this.postId = postId;
        this.productId = productId;
        this.memberId = memberId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.price = price;
        this.createdAt = createdAt;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
