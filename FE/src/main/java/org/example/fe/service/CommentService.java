package org.example.fe.service;

import org.example.fe.entity.CommentResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.model.CommentRequest;

import java.util.List;

public interface CommentService {
    ApiResponse<List<CommentResponse>> getAllComments(int postID);
    ApiResponse<CommentResponse> createComment(CommentRequest com);
}
