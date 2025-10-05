package org.example.fe.service;

import org.example.fe.entity.PostResponse;
import org.example.fe.model.response.ApiResponse;

import java.util.List;

public interface PostService {
    ApiResponse<List<PostResponse>> getAllPost();
    ApiResponse<List<PostResponse>> getLatestPost();
}
