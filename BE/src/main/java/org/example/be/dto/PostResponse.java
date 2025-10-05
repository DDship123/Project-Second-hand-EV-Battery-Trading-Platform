package org.example.be.dto;

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

    // seller info
    private Integer sellerId;
    private String sellerName;
    private String sellerAvatar;

    // product info
    private Integer productId;
    private String productName;
    private String productType;
    private String productStatus;

    // images
    private List<String> images;

    public PostResponse() {
    }

    public PostResponse(Integer postsId, String title, String description, String status, BigDecimal price,
                        LocalDateTime createdAt, Integer sellerId, String sellerName, String sellerAvatar,
                        Integer productId, String productName, String productType, String productStatus,
                        List<String> images) {
        this.postsId = postsId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.price = price;
        this.createdAt = createdAt;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerAvatar = sellerAvatar;
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productStatus = productStatus;
        this.images = images;
    }

    public Integer getPostsId() { return postsId; }
    public void setPostsId(Integer postsId) { this.postsId = postsId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Integer getSellerId() { return sellerId; }
    public void setSellerId(Integer sellerId) { this.sellerId = sellerId; }
    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }
    public String getSellerAvatar() { return sellerAvatar; }
    public void setSellerAvatar(String sellerAvatar) { this.sellerAvatar = sellerAvatar; }
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }
    public String getProductStatus() { return productStatus; }
    public void setProductStatus(String productStatus) { this.productStatus = productStatus; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}
