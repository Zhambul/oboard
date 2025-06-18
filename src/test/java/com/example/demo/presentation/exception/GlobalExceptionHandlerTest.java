package com.example.demo.presentation.exception;

import com.example.demo.domain.exception.DataNotFoundException;
import com.example.demo.domain.exception.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    
    private GlobalExceptionHandler exceptionHandler;
    private WebRequest webRequest;
    
    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/test");
        webRequest = new ServletWebRequest(request);
    }
    
    @Test
    void handleDataNotFoundException_ShouldReturnNotFound() {
        DataNotFoundException exception = new DataNotFoundException("Data not found");
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleDataNotFoundException(exception, webRequest);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("DATA_NOT_FOUND", response.getBody().getErrorCode());
        assertEquals("Data not found", response.getBody().getMessage());
        assertEquals("/test", response.getBody().getPath());
    }
    
    @Test
    void handleInvalidDataException_ShouldReturnBadRequest() {
        InvalidDataException exception = new InvalidDataException("Invalid data provided");
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleInvalidDataException(exception, webRequest);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("INVALID_DATA", response.getBody().getErrorCode());
        assertEquals("Invalid data provided", response.getBody().getMessage());
    }
    
    @Test
    void handleIllegalArgumentException_ShouldReturnBadRequest() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleIllegalArgumentException(exception, webRequest);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("INVALID_ARGUMENT", response.getBody().getErrorCode());
        assertEquals("Invalid argument", response.getBody().getMessage());
    }
    
    @Test
    void handleGenericException_ShouldReturnInternalServerError() {
        Exception exception = new Exception("Unexpected error");
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGenericException(exception, webRequest);
        
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("UNKNOWN_ERROR", response.getBody().getErrorCode());
        assertEquals("An unexpected error occurred", response.getBody().getMessage());
    }
}