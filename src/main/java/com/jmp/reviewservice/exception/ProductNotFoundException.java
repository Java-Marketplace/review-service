package com.jmp.reviewservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends CustomException {
    private static final String ERROR_MESSAGE = "Product ID %s not found";

    public ProductNotFoundException(Long productId) {
        super(String.format(ERROR_MESSAGE, productId), "PRODUCT_NOT_FOUND");
    }
}
