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
}
