package com.example.demo.domain.service.impl;

import com.example.demo.domain.model.Data;
import com.example.demo.domain.service.DataRetrievalService;
import com.example.demo.domain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

  private final DataRetrievalService dataRetrievalService;

  @Autowired
  public DataServiceImpl(DataRetrievalService dataRetrievalService) {
    this.dataRetrievalService = dataRetrievalService;
  }

  public Data getData() {
    List<String> data = dataRetrievalService.retrieveData();
    return new Data(String.join(" ", data));
  }
}