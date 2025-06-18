package com.example.demo.presentation.controller;

import com.example.demo.presentation.converter.DtoConverter;
import com.example.demo.presentation.dto.DataDTO;
import com.example.demo.domain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final DataService dataService;

  private final DtoConverter dtoConverter;

  @Autowired
  public TestController(DataService dataService, DtoConverter dtoConverter) {
    this.dataService = dataService;
    this.dtoConverter = dtoConverter;
  }

  @GetMapping("/test")
  public DataDTO getTestData() {
    var data = dataService.getData();
    return dtoConverter.toDataDto(data);
  }
}