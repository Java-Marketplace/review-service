package com.jmp.reviewservice.exception;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends CustomException {
    private static final String ERROR = "User not found";

    public ReviewNotFoundException(Long reviewId) {
        super(
                HttpStatus.NOT_FOUND,
                ERROR,
                "REVIEW_NOT_FOUND",
                reviewId
        );
    }
}