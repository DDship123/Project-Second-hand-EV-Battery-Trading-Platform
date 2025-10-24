package org.example.be.service;

import org.example.be.dto.response.MemberResponse;
import org.example.be.dto.response.ReviewResponse;
import org.example.be.entity.Review;
import org.example.be.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.orElse(null);
    }

    public Review getReviewByTransactionId(Integer transactionId) {
        return reviewRepository.findByTransaction_TransactionsId(transactionId).orElse(null);
    }

    public Review updateReview(Integer id, Review reviewDetails) {
        Review review = getReviewById(id);
        if (review == null) {
            return null;
        }
        review.setSeller(reviewDetails.getSeller());
        review.setReviewer(reviewDetails.getReviewer());
        review.setTransaction(reviewDetails.getTransaction());
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        review.setStatus(reviewDetails.getStatus());
        review.setCreatedAt(reviewDetails.getCreatedAt());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> findAllReviewBySellerId(Integer sellerId) {
        List<Review> reviews = reviewRepository.findAllBySeller_MemberId(sellerId);
        for (Review review : reviews) {
            if (!review.getStatus().equals("APPROVED")) {
                reviews.remove(review);
            }
        }
        return reviews;
    }
    //Lấy tất cả review theo status(Tân)
    public List<ReviewResponse> findAllReviewByStatus(String status) {
        List<Review> reviews = reviewRepository.findAllByStatus(status);
        return reviews.stream().map(review -> {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setReviewId(review.getReviewsId());
            reviewResponse.setRating(review.getRating());
            reviewResponse.setComment(review.getComment());
            reviewResponse.setStatus(review.getStatus());
            reviewResponse.setCreatedAt(review.getCreatedAt());

            // Map seller
            if (review.getSeller() != null) {
                MemberResponse seller = new MemberResponse();
                seller.setMemberId(review.getSeller().getMemberId());
                seller.setUsername(review.getSeller().getUsername());
                seller.setEmail(review.getSeller().getEmail());
                seller.setAvatarUrl(review.getSeller().getAvatarUrl());
                reviewResponse.setSeller(seller);
            }

            // Map reviewer
            if (review.getReviewer() != null) {
                MemberResponse reviewer = new MemberResponse();
                reviewer.setMemberId(review.getReviewer().getMemberId());
                reviewer.setUsername(review.getReviewer().getUsername());
                reviewer.setEmail(review.getReviewer().getEmail());
                reviewer.setAvatarUrl(review.getReviewer().getAvatarUrl());
                reviewResponse.setReviewer(reviewer);
            }

            return reviewResponse;
        }).collect(Collectors.toList());
    }
}

