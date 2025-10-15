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
    ApiResponse<List<PostResponse>> getLatestPosts();
    ApiResponse<List<PostResponse>> getLatestVehiclePosts();
    ApiResponse<List<PostResponse>> getLatestBatteryPosts();
    ApiResponse<List<PostResponse>> findAllPostByMemberCity(String city);
    ApiResponse<List<PostResponse>> findAllPostByMemberCityAndProductType(String city,String productType);
    ApiResponse<List<PostResponse>> findAllPostByMemberCityAndProductTypeAndTitle(String productType,String city,String title);
    ApiResponse<List<PostResponse>> getAllPostByMemberId(int memberId);
    ApiResponse<PostResponse> create (PostResponse post);
    ApiResponse<PostResponse> delete (int postId);

}
