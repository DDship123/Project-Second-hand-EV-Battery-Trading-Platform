package org.example.be.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "post_images")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_images_id")
    private Integer postImagesId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "image_url", columnDefinition = "NVARCHAR(500)")
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