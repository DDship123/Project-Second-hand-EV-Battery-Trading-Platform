package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_images")
@Data
@Getter
@Setter
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