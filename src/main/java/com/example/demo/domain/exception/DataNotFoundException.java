package com.example.demo.domain.exception;

public class DataNotFoundException extends BusinessException {
    
    public DataNotFoundException(String message) {
        super(message, "DATA_NOT_FOUND");
    }
    
    public DataNotFoundException(String message, Throwable cause) {
        super(message, "DATA_NOT_FOUND", cause);
    }
}