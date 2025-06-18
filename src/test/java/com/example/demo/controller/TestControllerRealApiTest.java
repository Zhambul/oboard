package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "app.google-sheets.url=https://docs.google.com/spreadsheets/d/1C5QBq8FEnuvWWVng2vmb0sbgGH-zA-qOUr1QLR8PE9A/export?format=xlsx"
})
class TestControllerRealApiTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testGetTestData_WithRealApi_ResponseIsString() {
        String url = "http://localhost:" + port + "/test";
        
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(String.class);
        
        String responseBody = response.getBody();

        assertThat(responseBody).isEqualTo("{\"data\":\"I want to work at Oboard !\"}");
    }

}