# OBoard - Excel Data Processing API

A Spring Boot application that retrieves data from Google Sheets Excel files and provides a REST API for accessing the processed data.

## Overview

OBoard is a data processing service that:
- Fetches Excel files from Google Sheets
- Parses Excel data using Apache POI
- Provides cached data access through REST endpoints
- Implements proper error handling and exception management

## Technology Stack

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Web** - REST API framework
- **Spring Cache** - Data caching
- **Apache POI 5.2.4** - Excel file processing
- **Gradle** - Build tool

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java                    # Main application class
├── config/
│   └── RestTemplateConfig.java           # REST client configuration
├── domain/
│   ├── exception/                         # Custom exceptions
│   ├── model/
│   │   └── Data.java                     # Data model
│   └── service/
│       ├── DataService.java             # Data service interface
│       ├── DataRetrievalService.java    # Data retrieval interface
│       └── impl/
│           └── DataServiceImpl.java     # Data service implementation
├── external/
│   └── excel/
│       ├── ExcelCellsParser.java        # Excel parsing logic
│       └── ExcelRetrievalService.java   # Google Sheets data retrieval
└── presentation/
    ├── controller/
    │   └── TestController.java          # REST API endpoints
    ├── converter/
    │   └── DtoConverter.java            # DTO conversion
    ├── dto/
    │   └── DataDTO.java                 # Data transfer objects
    └── exception/
        ├── ErrorResponse.java           # Error response model
        └── GlobalExceptionHandler.java # Global exception handling
```

## Configuration

### Application Properties

```properties
spring.application.name=demo
app.google-sheets.url=https://docs.google.com/spreadsheets/d/1C5QBq8FEnuvWWVng2vmb0sbgGH-zA-qOUr1QLR8PE9A/export?format=xlsx
```

### Environment Variables

The application uses the following configuration:
- `app.google-sheets.url` - URL to the Google Sheets Excel export endpoint

## API Endpoints

### GET /test

Retrieves processed data from the configured Google Sheets Excel file.

**Response:**
- **Content-Type:** `application/json`
- **Body:** `DataDTO` object containing the processed data

**Example:**
```json
{
  "data": "processed_excel_data"
}
```

## Features

### Data Processing
- Fetches Excel files from Google Sheets using configurable URLs
- Parses all cells from the first sheet of the Excel file
- Filters out empty cells and trims whitespace
- Handles various cell types (string, numeric, boolean, formula)
- Processes date-formatted cells appropriately

### Caching
- Implements Spring Cache with `@Cacheable` annotation
- Cache key: `excelData`
- Cache condition: `unless = "#result == null"`
- Improves performance by avoiding repeated external calls

### Error Handling
- Custom exception hierarchy with specific business exceptions
- Global exception handler for consistent error responses
- Proper error propagation and logging

## Building and Running

### Prerequisites
- Java 17
- Gradle (wrapper included)

### Build
```bash
./gradlew build
```

### Run
```bash
./gradlew bootRun
```

### Test
```bash
./gradlew test
```

The application will start on the default port 8080.

## Usage Examples

### Fetch Data
```bash
curl -X GET http://localhost:8080/test
```

### Response Example
```json
{
  "data": "Cell content from Excel sheet"
}
```

## Dependencies

### Core Dependencies
- `spring-boot-starter-web` - Web framework and REST API support
- `spring-boot-starter-cache` - Caching support
- `poi:5.2.4` - Apache POI for Excel processing
- `poi-ooxml:5.2.4` - Apache POI OOXML support

### Test Dependencies
- `spring-boot-starter-test` - Testing framework
- `junit-platform-launcher` - JUnit 5 platform

## Error Handling

The application includes comprehensive error handling with:

- **BusinessException** - General business logic errors
- **DataNotFoundException** - When requested data is not found
- **DataProcessingException** - Errors during data processing
- **InvalidDataException** - Invalid data format errors
- **GlobalExceptionHandler** - Centralized exception handling

## Development

### Adding New Endpoints
1. Create controller methods in `TestController`
2. Add corresponding service methods in `DataService`
3. Update DTOs and converters as needed

### Modifying Data Sources
1. Update `app.google-sheets.url` in `application.properties`
2. Modify `ExcelRetrievalService` if different data source types are needed

### Extending Data Processing
1. Enhance `ExcelCellsParser` for additional Excel features
2. Add new data models and DTOs for complex data structures