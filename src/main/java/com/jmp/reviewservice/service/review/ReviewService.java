package com.jmp.reviewservice.service.review;

import com.jmp.reviewservice.dto.request.CreateReviewRequest;
import com.jmp.reviewservice.dto.request.UpdateReviewRequest;
import com.jmp.reviewservice.dto.response.ReviewResponse;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<ReviewResponse> getAllReviewsByProduct(Long productId);

    ReviewResponse getReview(Long reviewId);

    ReviewResponse createReview(UUID userId, CreateReviewRequest createReviewRequest);

    ReviewResponse updateReview(Long reviewId, UpdateReviewRequest createReviewRequest);

    void deleteReview(Long reviewId);
}
