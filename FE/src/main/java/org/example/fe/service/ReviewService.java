package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ApiResponse<List<ReviewResponse>> getAllBuyerReview(int postId);
    ApiResponse<List<ReviewResponse>> FindAllReviewBySellerId(int sellerId);
    ApiResponse<List<ReviewResponse>> findAllReviewByStatus(String status);
    ApiResponse<ReviewResponse> update(ReviewResponse review);
    ApiResponse<ReviewResponse> create(int transactionId,int sellerId,int buyerId, int rating, String comment);

}
