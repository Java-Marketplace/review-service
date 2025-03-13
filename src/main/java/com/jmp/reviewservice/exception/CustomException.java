package com.jmp.reviewservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CustomException extends RuntimeException {
    private final String message;
    private final String code;
    private final transient Object[] args;
    private final HttpStatus status;

    protected CustomException(HttpStatus status, String message, String code, Object... args) {
        super(message);
        this.status = status;
        this.message = message;
        this.code = code;
        this.args = args;
    }
}
