package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_images")
<<<<<<< HEAD
@Data
@Getter
@Setter
=======
>>>>>>> 1b9bb453edb01b9d874fa923b552c1d83d011243
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_images_id")
    private Integer postImagesId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "image_url")
    private String imageUrl;

    public Integer getPostImagesId() {
        return postImagesId;
    }

    public void setPostImagesId(Integer postImagesId) {
        this.postImagesId = postImagesId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}