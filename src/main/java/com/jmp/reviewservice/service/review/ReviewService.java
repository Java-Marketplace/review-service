package com.jmp.reviewservice.service.review;

import com.jmp.reviewservice.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByProduct(Long productId);
    Review getReviewById(Long reviewId);
}
