package org.example.fe.service;

import org.example.fe.entity.ReviewResponse;
import org.example.fe.entity.ApiResponse;

import java.util.List;

public interface ReviewService {
    ApiResponse<List<ReviewResponse>> getAllBuyerReview(int postId);
}
