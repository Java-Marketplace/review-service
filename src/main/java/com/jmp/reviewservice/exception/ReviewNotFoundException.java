package com.jmp.reviewservice.exception;

public class ReviewNotFoundException extends CustomException {
    private static final String ERROR = "User not found";

    public ReviewNotFoundException(Long reviewId) {
        super(ERROR, "REVIEW_NOT_FOUND", reviewId);
    }
}