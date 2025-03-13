package com.jmp.reviewservice.dto.exception;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String code,
        String message,
        Instant timestamp
) {
}