package com.beno.theinventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WarehouseNotFoundException.class)
    public ResponseEntity<Object> handleWarehouseNotFoundException(WarehouseNotFoundException e) {
        Map<String, Object> body = Map.of(
                "message", e.getMessage(),
                "status", HttpStatus.NOT_FOUND,
                "error", "Not Found",
                "timestamp", LocalDateTime.now()
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<Object> handleSupplierNotFoundException(SupplierNotFoundException e) {
        Map<String, Object> body = Map.of(
                "message", e.getMessage(),
                "status", HttpStatus.NOT_FOUND,
                "error", "Not Found",
                "timestamp", LocalDateTime.now()
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception e) {
        Map<String, Object> body = Map.of(
                "message", e.getMessage(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR,
                "error", "Internal Server Error",
                "timestamp", LocalDateTime.now()
        );
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException e) {
        Map<String, Object> body = Map.of(
                "message", e.getMessage(),
                "status", HttpStatus.CONFLICT,
                "error", "Conflict",
                "timestamp", LocalDateTime.now()
        );
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFountException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFountException e) {
        Map<String, Object> body = Map.of(
                "message", e.getMessage(),
                "status", HttpStatus.NOT_FOUND,
                "error", "Not Found",
                "timestamp", LocalDateTime.now()
        );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockQuantityEditedInProductException.class)
    public ResponseEntity<Object> handleStockQuantityEditedInProductException(StockQuantityEditedInProductException e) {
        Map<String, Object> body = Map.of(
                "message", e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
