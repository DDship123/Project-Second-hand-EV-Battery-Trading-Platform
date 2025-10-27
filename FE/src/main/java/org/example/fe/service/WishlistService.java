package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.FavoriteResponse;
import org.example.fe.response.MemberResponse;

import java.util.List;

public interface WishlistService {
    ApiResponse<FavoriteResponse> getLatest(MemberResponse member);
    ApiResponse<List<FavoriteResponse>> getAll(int memberId);
    ApiResponse<FavoriteResponse> addWishlist(int memberId, int postId);
    ApiResponse<Boolean> deleteWishlist(int favoriteId);
    ApiResponse<FavoriteResponse> getFist(int memberId);
}
