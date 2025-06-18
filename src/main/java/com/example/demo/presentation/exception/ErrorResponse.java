package com.example.demo.presentation.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public ErrorResponse(String errorCode, String message, String path) {
        this();
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
}