package com.example.demo.domain.service.impl;

import com.example.demo.domain.model.Data;
import com.example.demo.domain.service.DataRetrievalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataServiceImplTest {

    @Mock
    private DataRetrievalService dataRetrievalService;

    private DataServiceImpl dataService;

    @BeforeEach
    void setUp() {
        dataService = new DataServiceImpl(dataRetrievalService);
    }

    @Test
    void getData_ShouldJoinMultipleStrings_WhenDataRetrievalServiceReturnsMultipleItems() {
        List<String> mockData = Arrays.asList("Hello", "World", "Test");
        when(dataRetrievalService.retrieveData()).thenReturn(mockData);

        Data result = dataService.getData();

        assertNotNull(result);
        assertEquals("Hello World Test", result.data());
        verify(dataRetrievalService, times(1)).retrieveData();
    }

    @Test
    void getData_ShouldReturnSingleString_WhenDataRetrievalServiceReturnsSingleItem() {
        List<String> mockData = Arrays.asList("OnlyOneItem");
        when(dataRetrievalService.retrieveData()).thenReturn(mockData);

        Data result = dataService.getData();

        assertNotNull(result);
        assertEquals("OnlyOneItem", result.data());
        verify(dataRetrievalService, times(1)).retrieveData();
    }

    @Test
    void getData_ShouldReturnEmptyString_WhenDataRetrievalServiceReturnsEmptyList() {
        List<String> mockData = Collections.emptyList();
        when(dataRetrievalService.retrieveData()).thenReturn(mockData);

        Data result = dataService.getData();

        assertNotNull(result);
        assertEquals("", result.data());
        verify(dataRetrievalService, times(1)).retrieveData();
    }

    @Test
    void getData_ShouldHandleNullValues_WhenDataRetrievalServiceReturnsListWithNulls() {
        List<String> mockData = Arrays.asList("Hello", null, "World");
        when(dataRetrievalService.retrieveData()).thenReturn(mockData);

        Data result = dataService.getData();

        assertNotNull(result);
        assertEquals("Hello null World", result.data());
        verify(dataRetrievalService, times(1)).retrieveData();
    }

    @Test
    void getData_ShouldHandleEmptyStrings_WhenDataRetrievalServiceReturnsEmptyStrings() {
        List<String> mockData = Arrays.asList("Hello", "", "World");
        when(dataRetrievalService.retrieveData()).thenReturn(mockData);

        Data result = dataService.getData();

        assertNotNull(result);
        assertEquals("Hello  World", result.data());
        verify(dataRetrievalService, times(1)).retrieveData();
    }

    @Test
    void getData_ShouldThrowException_WhenDataRetrievalServiceThrowsException() {
        RuntimeException expectedException = new RuntimeException("Service unavailable");
        when(dataRetrievalService.retrieveData()).thenThrow(expectedException);

        RuntimeException actualException = assertThrows(RuntimeException.class, () -> {
            dataService.getData();
        });

        assertEquals("Service unavailable", actualException.getMessage());
        verify(dataRetrievalService, times(1)).retrieveData();
    }

    @Test
    void getData_ShouldThrowException_WhenDataRetrievalServiceIsNull() {
        DataServiceImpl serviceWithNullDependency = new DataServiceImpl(null);
        
        assertThrows(NullPointerException.class, () -> {
            serviceWithNullDependency.getData();
        });
    }
}