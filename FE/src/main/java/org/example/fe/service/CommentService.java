package org.example.fe.service;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.CommentResponse;


import java.util.List;

public interface CommentService {
    ApiResponse<List<CommentResponse>> getAllComments(int postID);
    ApiResponse<CommentResponse> createComment(CommentResponse com);
    ApiResponse<List<CommentResponse>> FindAllCommentByPostId(int postId);
    ApiResponse<List<CommentResponse>> findAllCommentByStatus(String status);
    ApiResponse<CommentResponse> updateCommentStatus(int commentId, String status);
}
