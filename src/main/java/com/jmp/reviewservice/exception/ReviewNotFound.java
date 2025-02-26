package com.jmp.reviewservice.exception;

public class ReviewNotFound extends RuntimeException {
    public ReviewNotFound(String message) {
        super(message);
    }
}
