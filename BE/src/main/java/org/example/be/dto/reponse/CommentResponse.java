package org.example.be.dto.reponse;

import org.example.be.entity.Member;
import org.example.be.entity.Post;

import java.time.LocalDateTime;

public class CommentResponse {
    private int commentId;
    private PostResponse post;
    private MemberResponse member;
    private int rating;
    private String comment;
    private String status;
    private String createdAt;

    public CommentResponse() {
    }

    public CommentResponse(int commentId, PostResponse post, MemberResponse member,
                           int rating, String comment, String status, String createdAt) {
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

    public PostResponse getPost() {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setPost(PostResponse post) {
        this.post = post;
    }
}
