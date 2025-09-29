package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Integer postsId;

    @Column(name = "product_id", unique = true)
    private Integer productId;

    @Column(name = "member_id")
    private Integer memberId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status;

    private BigDecimal price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}