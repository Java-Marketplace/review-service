package com.jmp.reviewservice.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {
    private final String message;
    private final String code;
    private final transient Object[] args;

    protected CustomException(String message, String code, Object... args) {
        super(message);
        this.message = message;
        this.code = code;
        this.args = args;
    }
}
