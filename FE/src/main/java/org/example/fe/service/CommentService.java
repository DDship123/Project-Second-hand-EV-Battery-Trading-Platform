package org.example.fe.service;

import org.example.fe.entity.CommentResponse;
import org.example.fe.entity.ApiResponse;


import java.util.List;

public interface CommentService {
    ApiResponse<List<CommentResponse>> getAllComments(int postID);
    ApiResponse<CommentResponse> createComment(CommentResponse com);
}
