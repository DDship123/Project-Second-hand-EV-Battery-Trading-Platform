package org.example.fe.service;

import org.example.fe.entity.FavoriteResponse;
import org.example.fe.entity.ApiResponse;

import java.util.List;

public interface WishlistService {
    ApiResponse<FavoriteResponse> getLatest(int memberId);
    ApiResponse<List<FavoriteResponse>> getAll(int memberId);
    ApiResponse<FavoriteResponse> addWishlist(int memberId, int postId);
    ApiResponse<FavoriteResponse> deleteWishlist(int memberId, int postId);
}
