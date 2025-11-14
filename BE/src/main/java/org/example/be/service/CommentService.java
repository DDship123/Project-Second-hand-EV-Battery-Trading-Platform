package org.example.be.service;

import org.example.be.dto.response.CommentResponse;
import org.example.be.entity.Comment;

import java.util.List;

public interface CommentService {
    public Comment createComment(Comment comment);
    public List<Comment> getAllComments();
    public Comment getCommentById(Integer id);
    public Comment updateComment(Integer id, Comment commentDetails);
    public void deleteComment(Integer id);
    public List<CommentResponse> findAllCommentByPostId(Integer postId);
    public List<CommentResponse> findAllCommentByStatus(String status);

}
