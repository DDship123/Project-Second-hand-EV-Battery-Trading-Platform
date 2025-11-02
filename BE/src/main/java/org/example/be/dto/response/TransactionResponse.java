package org.example.be.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    private Integer transactionId;
    private MemberResponse buyer;
    private MemberResponse seller;
    private PostResponse post;
    private String postTitle;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
    private String imageUrl;
    private ReviewResponse review;


    public TransactionResponse() {
    }

    public TransactionResponse(Integer transactionId, MemberResponse buyer
            , String buyerName, MemberResponse seller
            , PostResponse post, String postTitle, BigDecimal price
            , String status, LocalDateTime createdAt, String imageUrl) {
        this.transactionId = transactionId;
        this.buyer = buyer;
        this.seller = seller;
        this.post = post;
        this.postTitle = postTitle;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public ReviewResponse getReview() {
        return review;
    }

    public void setReview(ReviewResponse review) {
        this.review = review;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public MemberResponse getBuyer() {
        return buyer;
    }

    public void setBuyer(MemberResponse buyer) {
        this.buyer = buyer;
    }

    public MemberResponse getSeller() {
        return seller;
    }

    public void setSeller(MemberResponse seller) {
        this.seller = seller;
    }

    public PostResponse getPost() {
        return post;
    }

    public void setPost(PostResponse post) {
        this.post = post;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
