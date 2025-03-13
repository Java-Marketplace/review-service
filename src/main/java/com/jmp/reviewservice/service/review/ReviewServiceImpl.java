package com.jmp.reviewservice.service.review;

import com.jmp.reviewservice.dto.review.CreateReviewRequest;
import com.jmp.reviewservice.dto.review.ReviewResponse;
import com.jmp.reviewservice.dto.review.UpdateReviewRequest;
import com.jmp.reviewservice.exception.ReviewNotFoundException;
import com.jmp.reviewservice.mapper.ReviewMapper;
import com.jmp.reviewservice.model.Review;
import com.jmp.reviewservice.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewResponse> getAllReviewsByProduct(Long productId) {
        return reviewMapper.toResponseList(reviewRepository.findAllByProductId(productId));
    }

    @Override
    public ReviewResponse getReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        return reviewMapper.toResponse(review);
    }

    @Override
    @Transactional
    public ReviewResponse createReview(Long userId, CreateReviewRequest createReviewRequest) {
        Review review = reviewMapper.toEntity(createReviewRequest);
        review.setUserId(userId);
        reviewRepository.save(review);
        return reviewMapper.toResponse(review);
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Long reviewId, UpdateReviewRequest updateReviewRequest) {
        Review review = getReviewById(reviewId);
        reviewMapper.updateReviewFromRequest(updateReviewRequest, review);
        reviewRepository.save(review);
        return reviewMapper.toResponse(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        getReviewById(reviewId);
        reviewRepository.deleteById(reviewId);
    }

    private Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException(reviewId));
    }
}
