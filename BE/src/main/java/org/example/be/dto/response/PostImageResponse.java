package org.example.be.dto.response;

public class PostImageResponse {
    private int postImagesId;
    private int postId;
    private String imageUrl;

    public PostImageResponse() {
    }

    public PostImageResponse(int postImagesId, int postId, String imageUrl) {
        this.postImagesId = postImagesId;
        this.postId = postId;
        this.imageUrl = imageUrl;
    }

    public int getPostImagesId() {
        return postImagesId;
    }

    public void setPostImagesId(int postImagesId) {
        this.postImagesId = postImagesId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
