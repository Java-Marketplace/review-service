package com.jmp.reviewservice.exception.handler;

import com.jmp.reviewservice.dto.exception.ErrorResponse;
import com.jmp.reviewservice.exception.CustomException;
import com.jmp.reviewservice.mapper.ErrorResponseMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = errorResponseMapper.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("Incorrect data in the request");

        return new ErrorResponse(
                HttpStatus.valueOf(response.getStatus()),
                "VALIDATION_ERROR",
                errorMessage,
                Instant.now()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalException(Exception ex, HttpServletResponse response) {
        log.error("Internal server error", ex);

        return new ErrorResponse(
                HttpStatus.valueOf(response.getStatus()),
                "INTERNAL_ERROR",
                "Internal Server Error",
                Instant.now()
        );
    }
}
