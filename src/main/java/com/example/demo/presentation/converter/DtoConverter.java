package com.example.demo.presentation.converter;

import com.example.demo.domain.model.Data;
import com.example.demo.presentation.dto.DataDTO;
import org.springframework.stereotype.Service;

@Service
public class DtoConverter {

  public DataDTO toDataDto(Data model) {
    if (model == null) {
      return null;
    }
    return new DataDTO(model.data());
  }
}
