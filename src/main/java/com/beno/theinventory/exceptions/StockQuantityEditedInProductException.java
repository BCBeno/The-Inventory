package com.beno.theinventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StockQuantityEditedInProductException extends RuntimeException {
    public StockQuantityEditedInProductException(String message) {
        super(message);
    }
}
