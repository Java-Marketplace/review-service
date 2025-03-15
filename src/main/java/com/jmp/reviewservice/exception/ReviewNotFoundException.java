package com.jmp.reviewservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends CustomException {
    private static final String ERROR = "User not found";

    public ReviewNotFoundException(Long reviewId) {
        super(ERROR, "REVIEW_NOT_FOUND", reviewId);
    }
}