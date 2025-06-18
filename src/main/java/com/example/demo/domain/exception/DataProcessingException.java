package com.example.demo.domain.exception;

public class DataProcessingException extends BusinessException {
    
    public DataProcessingException(String message) {
        super(message, "DATA_PROCESSING_ERROR");
    }
    
    public DataProcessingException(String message, Throwable cause) {
        super(message, "DATA_PROCESSING_ERROR", cause);
    }
}