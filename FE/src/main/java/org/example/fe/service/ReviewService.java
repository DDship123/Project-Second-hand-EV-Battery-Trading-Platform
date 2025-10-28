package org.example.fe.service;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ApiResponse<List<ReviewResponse>> getAllBuyerReview(int postId);
    ApiResponse<List<ReviewResponse>> FindAllReviewBySellerId(int sellerId);
    ApiResponse<List<ReviewResponse>> findAllReviewByStatus(String status);
    ApiResponse<ReviewResponse> update(ReviewResponse review);
    ApiResponse<ReviewResponse> create(ReviewResponse review);

}
