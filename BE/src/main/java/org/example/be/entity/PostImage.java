package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "post_images")
@Data
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_images_id")
    private Integer postImagesId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "image_url")
    private String imageUrl;
}