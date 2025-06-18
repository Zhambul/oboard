package com.example.demo.external.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelCellsParser {

  public List<String> parse(byte[] excelData) {
    List<String> cellValues = new ArrayList<>();

    try (ByteArrayInputStream inputStream = new ByteArrayInputStream(excelData);
         Workbook workbook = new XSSFWorkbook(inputStream)) {

      Sheet sheet = workbook.getSheetAt(0);

      for (Row row : sheet) {
        for (Cell cell : row) {
          String cellValue = getCellValueAsString(cell);
          if (cellValue != null && !cellValue.trim().isEmpty()) {
            cellValues.add(cellValue.trim());
          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return cellValues;
  }

  private String getCellValueAsString(Cell cell) {
    if (cell == null) {
      return null;
    }

    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue().toString();
        } else {
          return String.valueOf(cell.getNumericCellValue());
        }
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
        CellValue cellValue = evaluator.evaluate(cell);
        return getCellValueFromEvaluatedCell(cellValue);
      default:
        return null;
    }
  }

  private String getCellValueFromEvaluatedCell(CellValue cellValue) {
    return switch (cellValue.getCellType()) {
      case STRING -> cellValue.getStringValue();
      case NUMERIC -> String.valueOf(cellValue.getNumberValue());
      case BOOLEAN -> String.valueOf(cellValue.getBooleanValue());
      default -> null;
    };
  }
}
