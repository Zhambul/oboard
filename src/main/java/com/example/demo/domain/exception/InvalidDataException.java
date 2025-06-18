package com.example.demo.domain.exception;

public class InvalidDataException extends BusinessException {
    
    public InvalidDataException(String message) {
        super(message, "INVALID_DATA");
    }
    
    public InvalidDataException(String message, Throwable cause) {
        super(message, "INVALID_DATA", cause);
    }
}