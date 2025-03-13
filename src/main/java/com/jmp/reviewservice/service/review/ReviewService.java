package com.jmp.reviewservice.service.review;

import com.jmp.reviewservice.dto.review.CreateReviewRequest;
import com.jmp.reviewservice.dto.review.ReviewResponse;
import com.jmp.reviewservice.dto.review.UpdateReviewRequest;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getAllReviewsByProduct(Long productId);

    ReviewResponse getReview(Long reviewId);

    ReviewResponse createReview(Long userId, CreateReviewRequest createReviewRequest);

    ReviewResponse updateReview(Long reviewId, UpdateReviewRequest createReviewRequest);

    void deleteReview(Long reviewId);
}
