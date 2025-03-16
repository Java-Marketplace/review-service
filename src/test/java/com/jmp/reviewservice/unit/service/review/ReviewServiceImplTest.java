package com.jmp.reviewservice.unit.service.review;

import com.jmp.reviewservice.dto.request.CreateReviewRequest;
import com.jmp.reviewservice.dto.response.ReviewResponse;
import com.jmp.reviewservice.exception.ProductNotFoundException;
import com.jmp.reviewservice.exception.ReviewNotFoundException;
import com.jmp.reviewservice.mapper.ReviewMapper;
import com.jmp.reviewservice.model.Review;
import com.jmp.reviewservice.repository.ReviewRepository;
import com.jmp.reviewservice.service.review.ReviewServiceImpl;
import com.jmp.reviewservice.support.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class ReviewServiceImplTest extends BaseUnitTest {
    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void getAllReviewsByProductId_WithExistingProduct_ReturnsReviewResponseList() {
        Long productId = 1L;
        List<Review> reviews = List.of(new Review(), new Review());

        when(reviewRepository.findAllByProductId(productId)).thenReturn(reviews);
        when(reviewMapper.toResponseList(reviews)).thenReturn(List.of(new ReviewResponse(), new ReviewResponse()));

        List<ReviewResponse> result = reviewService.getAllReviewsByProduct(productId);

        assertEquals(2, result.size());
        verify(reviewRepository).findAllByProductId(productId);
        verify(reviewMapper).toResponseList(reviews);
    }

    @Test
    void getAllReviewsByProduct_WithNonExistentProduct_ThrowsException() {
        Long productId = 999L;
        when(reviewRepository.findAllByProductId(productId)).thenReturn(Collections.emptyList());

        assertThrows(ProductNotFoundException.class,
                () -> reviewService.getAllReviewsByProduct(productId));
        verify(reviewRepository).findAllByProductId(productId);
    }

    @Test
    void getReview_WithValidId_ReturnsReviewResponse() {
        Review review = new Review();
        review.setId(1L);
        ReviewResponse expectedResponse = new ReviewResponse();
        expectedResponse.setId(1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewMapper.toResponse(review)).thenReturn(expectedResponse);

        ReviewResponse actualResponse = reviewService.getReview(1L);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(1L, actualResponse.getId());

        verify(reviewRepository).findById(1L);
        verify(reviewMapper).toResponse(review);
        verifyNoMoreInteractions(reviewRepository, reviewMapper);
    }

    @Test
    void getReview_WithNonExistentId_ThrowsNotFoundException() {
        Long invalidId = 999L;
        when(reviewRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class,
                () -> reviewService.getReview(invalidId));
        verify(reviewRepository).findById(invalidId);
    }

    @Test
    void createReview_WithValidRequest_ReturnsResponse() {
        UUID userId = UUID.randomUUID();
        CreateReviewRequest request = new CreateReviewRequest(1L, 5, "Отличный товар!", null, null);
        Review savedReview = new Review();
        savedReview.setUserId(userId);

        when(reviewMapper.toEntity(request)).thenReturn(savedReview);
        when(reviewRepository.save(savedReview)).thenReturn(savedReview);
        when(reviewMapper.toResponse(savedReview)).thenReturn(new ReviewResponse());

        ReviewResponse result = reviewService.createReview(userId, request);

        assertNotNull(result);
        verify(reviewMapper).toEntity(request);
        verify(reviewRepository).save(savedReview);
        verify(reviewMapper).toResponse(savedReview);
        assertEquals(userId, savedReview.getUserId());
    }
}
