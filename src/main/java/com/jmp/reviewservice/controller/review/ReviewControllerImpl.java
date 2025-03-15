package com.jmp.reviewservice.controller.review;

import com.jmp.reviewservice.dto.review.CreateReviewRequest;
import com.jmp.reviewservice.dto.review.ReviewResponse;
import com.jmp.reviewservice.dto.review.UpdateReviewRequest;
import com.jmp.reviewservice.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
