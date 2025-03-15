package com.jmp.reviewservice.controller.review;

import com.jmp.reviewservice.dto.request.CreateReviewRequest;
import com.jmp.reviewservice.dto.request.UpdateReviewRequest;
import com.jmp.reviewservice.dto.response.ReviewResponse;
import com.jmp.reviewservice.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {
    private final ReviewService reviewService;

    @Override
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getAllReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getAllReviewsByProduct(productId);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(@RequestBody @Valid CreateReviewRequest createReviewRequest) {
        return reviewService.createReview(UUID.randomUUID(), createReviewRequest);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReviewResponse updateReview(@PathVariable Long id, @RequestBody @Valid UpdateReviewRequest updateReviewRequest) {
        return reviewService.updateReview(id, updateReviewRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
