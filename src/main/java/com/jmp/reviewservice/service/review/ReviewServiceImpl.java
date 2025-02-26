package com.jmp.reviewservice.service.review;

import com.jmp.reviewservice.exception.ReviewNotFound;
import com.jmp.reviewservice.model.Review;
import com.jmp.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviewsByProduct(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFound("Review with id " + reviewId + " not found"));
    }
}
