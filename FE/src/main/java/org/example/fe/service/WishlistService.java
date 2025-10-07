package org.example.fe.service;

import org.example.fe.entity.FavoriteResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;

import java.util.List;

public interface WishlistService {
    ApiResponse<FavoriteResponse> getLatest(MemberResponse member);
    ApiResponse<List<FavoriteResponse>> getAll(int memberId);
    ApiResponse<FavoriteResponse> addWishlist(int memberId, int postId);
    ApiResponse<Boolean> deleteWishlist(int favoriteId);
    ApiResponse<FavoriteResponse> getFist(int memberId);
}
