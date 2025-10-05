package org.example.fe.service;

import org.example.fe.entity.FavoriteResponse;
import org.example.fe.model.response.ApiResponse;

import java.util.List;

public interface WishlistService {
    ApiResponse<FavoriteResponse> getLatest(int memberId);
    ApiResponse<List<FavoriteResponse>> getAll(int memberId);
}
