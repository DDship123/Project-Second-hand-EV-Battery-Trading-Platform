package org.example.be.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Integer postsId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;

    @Column(columnDefinition = "NVARCHAR(200)")
    private String title;

    @Column(columnDefinition = "NTEXT")
    private String description;

    @Column(columnDefinition = "NVARCHAR(20)")
    private String status;

    private BigDecimal price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;



    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Member getSeller() {
        return seller;
    }

    public void setSeller(Member seller) {
        this.seller = seller;
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostImage> postImages = new ArrayList<>();

    public List<PostImage> getPostImages() {
        return postImages;
    }
    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction>  transactions = new ArrayList<>();
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @OneToMany(mappedBy = "post",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorite> favorites = new ArrayList<>();
    public List<Favorite> getFavorites() {
        return favorites;
    }
    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
}