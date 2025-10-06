package org.example.fe.service;

import org.example.fe.entity.PostResponse;
import org.example.fe.entity.ApiResponse;

import java.util.List;

public interface PostService {
    ApiResponse<List<PostResponse>> getAllPost();
    ApiResponse<List<PostResponse>> getLatestPost();
    ApiResponse<List<PostResponse>> getAllPostBatery();
    ApiResponse<List<PostResponse>> getAllPostVehicle();
    ApiResponse<PostResponse>getPostDetail(int postID);


}
