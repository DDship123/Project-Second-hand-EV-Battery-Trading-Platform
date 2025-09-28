package org.example.fe.entity;


import java.time.LocalDateTime;

public class CommentResponse {
    private int commentId;
    private int postId;
    private int memberId;
    private int rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;

    public CommentResponse() {
    }

    public CommentResponse(int commentId, int postId, int memberId, int rating, String comment, String status, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.memberId = memberId;
        this.rating = rating;
        this.comment = comment;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
