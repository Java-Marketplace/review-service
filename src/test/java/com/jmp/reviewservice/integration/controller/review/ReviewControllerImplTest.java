package com.jmp.reviewservice.integration.controller.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jmp.reviewservice.dto.review.CreateReviewRequest;
import com.jmp.reviewservice.dto.review.UpdateReviewRequest;
import com.jmp.reviewservice.model.Review;
import com.jmp.reviewservice.repository.ReviewRepository;
import com.jmp.reviewservice.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewControllerImplTest extends BaseIntegrationTest {
    private static final Long PRODUCT_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DataSet("dataset/reviews.yaml")
    void getAllReviewsByProductId_Success() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/" + PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].rating").value(5));
    }

    @Test
    void getAllReviewsByProductId_NotFound() throws Exception {
        mockMvc.perform(get("/api/v1/reviews/" + PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void createReview_Success() throws Exception {
        CreateReviewRequest request = new CreateReviewRequest(2L, 5, "Advantage", "Disadvantage", null);

        String jsonBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.advantage").value("Advantage"))
                .andExpect(jsonPath("$.disadvantage").value("Disadvantage"));
    }

    @Test
    @DataSet("dataset/reviews.yaml")
    @Transactional
    void updateReview_Success() throws Exception {
        UpdateReviewRequest request = new UpdateReviewRequest(4, null, null, null);

        String jsonBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/api/v1/reviews/" + PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(4));
    }

    @Test
    @DataSet("dataset/reviews.yaml")
    @Transactional
    void deleteReviewById_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/reviews/" + PRODUCT_ID))
                .andExpect(status().isNoContent());

        Review review = reviewRepository.findById(PRODUCT_ID).orElse(null);
        assertNull(review);
    }
}