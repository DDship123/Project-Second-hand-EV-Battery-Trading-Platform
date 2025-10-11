package org.example.be.dto.reponse;

import org.example.be.entity.Member;
import org.example.be.entity.Post;

import java.time.LocalDateTime;

public class CommentResponse {
    private int commentId;
    private Post post;
    private MemberResponse member;
    private int rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;

    public CommentResponse() {
    }

    public CommentResponse(int commentId, Post post, MemberResponse member, int rating, String comment, String status, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.post = post;
        this.member = member;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public MemberResponse getMember() {
        return member;
    }

    public void setMember(MemberResponse member) {
        this.member = member;
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
