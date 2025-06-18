package com.example.demo.external.excel;

import com.example.demo.domain.service.DataRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExcelRetrievalService implements DataRetrievalService {

  @Value("${app.google-sheets.url}")
  private String googleSheetsUrl;

  private final RestTemplate restTemplate;
  private final ExcelCellsParser excelCellsParser;

  @Autowired
  public ExcelRetrievalService(RestTemplate restTemplate, ExcelCellsParser excelCellsParser) {
    this.restTemplate = restTemplate;
    this.excelCellsParser = excelCellsParser;
  }

  @Override
  @Cacheable(value = "excelData", unless = "#result == null")
  public List<String> retrieveData() {
    try {
      byte[] excelData = restTemplate.getForObject(googleSheetsUrl, byte[].class);

      if (excelData == null) {
        throw new RuntimeException("Failed to fetch Excel file: received null data");
      }

      return excelCellsParser.parse(excelData);
    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch and process Excel data", e);
    }
  }


}