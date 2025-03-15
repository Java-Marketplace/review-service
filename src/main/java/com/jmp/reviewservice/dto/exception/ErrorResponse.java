package com.jmp.reviewservice.dto.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponse(
        HttpStatus status,
        String code,
        String message,
        Instant timestamp
) {
}