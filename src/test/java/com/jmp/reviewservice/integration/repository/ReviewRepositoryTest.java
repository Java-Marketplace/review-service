package com.jmp.reviewservice.integration.repository;

import com.jmp.reviewservice.model.Review;
import com.jmp.reviewservice.repository.ReviewRepository;
import com.jmp.reviewservice.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewRepositoryTest extends BaseIntegrationTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void findAllByProductId() {
        Review review = new Review();
        review.setRating(5);
        review.setUserId(UUID.randomUUID());
        review.setProductId(1L);
        reviewRepository.save(review);

        List<Review> reviewList = reviewRepository.findAllByProductId(1L);

        assertEquals(1, reviewList.size());
        assertEquals(1L, reviewList.getFirst().getProductId());
    }
}
