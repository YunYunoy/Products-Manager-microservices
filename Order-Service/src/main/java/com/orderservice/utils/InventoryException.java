package com.orderservice.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InventoryException extends RuntimeException {
    public InventoryException(String message) {
        super(message);
    }
}
