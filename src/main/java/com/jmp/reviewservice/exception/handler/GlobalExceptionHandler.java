package com.jmp.reviewservice.exception.handler;

import com.jmp.reviewservice.dto.exception.ErrorResponse;
import com.jmp.reviewservice.exception.CustomException;
import com.jmp.reviewservice.mapper.ErrorResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorResponseMapper errorResponseMapper;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = errorResponseMapper.toErrorResponse(ex);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }
}
