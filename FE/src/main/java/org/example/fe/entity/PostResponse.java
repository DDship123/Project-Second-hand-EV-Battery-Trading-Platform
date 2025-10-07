package org.example.fe.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PostResponse {
    private Integer postsId;
    private String title;
    private String description;
    private String status;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private MemberResponse seller;
    private ProductResponse product;

    // seller info
//    private Integer sellerId;
//    private String sellerName;
//    private String sellerAvatar;
//    private String sellerAddress;
//    private String sellerCity;

    // product info
//    private Integer productId;
//    private String productName;
//    private String productType;
//    private String productStatus;

    // images
    private List<String> images;
    private List<PostImageResponse> postImages;

    public PostResponse() {
    }

    public MemberResponse getSeller() {
        return seller;
    }

    public void setSeller(MemberResponse seller) {
        this.seller = seller;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<PostImageResponse> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImageResponse> postImages) {
        this.postImages = postImages;
    }
}
