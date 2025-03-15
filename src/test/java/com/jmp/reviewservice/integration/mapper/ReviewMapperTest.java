package com.jmp.reviewservice.integration.mapper;

import com.jmp.reviewservice.dto.request.CreateReviewRequest;
import com.jmp.reviewservice.dto.request.UpdateReviewRequest;
import com.jmp.reviewservice.dto.response.ReviewResponse;
import com.jmp.reviewservice.mapper.ReviewMapper;
import com.jmp.reviewservice.model.Review;
import com.jmp.reviewservice.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ReviewMapperTest extends BaseIntegrationTest {
    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    void testToEntity() {
        CreateReviewRequest request = new CreateReviewRequest();
        request.setProductId(1L);
        request.setRating(4);
        request.setAdvantage("Хорошее качество");
        request.setDisadvantage("Высокая цена");
        request.setComment("Отличный продукт, но дороговат.");

        Review review = reviewMapper.toEntity(request);

        assertNull(review.getId());
        assertNull(review.getUserId());
        assertEquals(1L, review.getProductId());
        assertEquals(4, review.getRating());
        assertEquals("Хорошее качество", review.getAdvantage());
        assertEquals("Высокая цена", review.getDisadvantage());
        assertEquals("Отличный продукт, но дороговат.", review.getComment());
        assertNull(review.getLikeCount());
        assertNull(review.getDislikeCount());
        assertNotNull(review.getAnswers());
        assertTrue(review.getAnswers().isEmpty());
        assertNull(review.getCreatedAt());
        assertNull(review.getUpdatedAt());
    }

    @Test
    void testToResponse() {
        Review review = new Review();
        review.setId(1L);
        review.setProductId(1L);
        review.setUserId(UUID.randomUUID());
        review.setRating(5);
        review.setAdvantage("Отличное качество");
        review.setDisadvantage("Нет");
        review.setComment("Очень доволен покупкой.");
        review.setLikeCount(10L);
        review.setDislikeCount(2L);

        ReviewResponse response = reviewMapper.toResponse(review);

        assertEquals(1L, response.getId());
        assertEquals(1L, response.getProductId());
        assertEquals(review.getUserId(), response.getUserId());
        assertEquals(5, response.getRating());
        assertEquals("Отличное качество", response.getAdvantage());
        assertEquals("Нет", response.getDisadvantage());
        assertEquals("Очень доволен покупкой.", response.getComment());
    }

    @Test
    void testToResponseList() {
        Review review1 = new Review();
        review1.setId(1L);
        review1.setProductId(1L);
        review1.setUserId(UUID.randomUUID());
        review1.setRating(5);
        review1.setAdvantage("Отличное качество");
        review1.setDisadvantage("Нет");
        review1.setComment("Очень доволен покупкой.");
        review1.setLikeCount(10L);
        review1.setDislikeCount(2L);

        Review review2 = new Review();
        review2.setId(2L);
        review2.setProductId(2L);
        review2.setUserId(UUID.randomUUID());
        review2.setRating(4);
        review2.setAdvantage("Хорошее качество");
        review2.setDisadvantage("Высокая цена");
        review2.setComment("Хороший продукт, но дороговат.");
        review2.setLikeCount(5L);
        review2.setDislikeCount(1L);

        List<Review> reviews = Arrays.asList(review1, review2);

        List<ReviewResponse> responses = reviewMapper.toResponseList(reviews);

        assertEquals(2, responses.size());

        ReviewResponse response1 = responses.getFirst();
        assertEquals(1L, response1.getId());
        assertEquals(1L, response1.getProductId());
        assertEquals(review1.getUserId(), response1.getUserId());
        assertEquals(5, response1.getRating());
        assertEquals("Отличное качество", response1.getAdvantage());
        assertEquals("Нет", response1.getDisadvantage());
        assertEquals("Очень доволен покупкой.", response1.getComment());

        ReviewResponse response2 = responses.get(1);
        assertEquals(2L, response2.getId());
        assertEquals(2L, response2.getProductId());
        assertEquals(review2.getUserId(), response2.getUserId());
        assertEquals(4, response2.getRating());
        assertEquals("Хорошее качество", response2.getAdvantage());
        assertEquals("Высокая цена", response2.getDisadvantage());
        assertEquals("Хороший продукт, но дороговат.", response2.getComment());
    }

    @Test
    void testUpdateReviewFromRequest() {
        Review review = new Review();
        review.setId(1L);
        review.setProductId(1L);
        review.setUserId(UUID.randomUUID());
        review.setRating(3);
        review.setAdvantage("Неплохое качество");
        review.setDisadvantage("Средняя цена");
        review.setComment("Ожидал большего.");
        review.setLikeCount(2L);
        review.setDislikeCount(3L);

        UpdateReviewRequest request = new UpdateReviewRequest();
        request.setRating(4);
        request.setAdvantage("Хорошее качество");
        request.setDisadvantage("Высокая цена");
        request.setComment("Продукт оправдал ожидания.");

        reviewMapper.updateReviewFromRequest(request, review);

        assertEquals(1L, review.getId());
        assertEquals(1L, review.getProductId());
        assertNotNull(review.getUserId());
        assertEquals(4, review.getRating());
        assertEquals("Хорошее качество", review.getAdvantage());
        assertEquals("Высокая цена", review.getDisadvantage());
        assertEquals("Продукт оправдал ожидания.", review.getComment());
    }
}
