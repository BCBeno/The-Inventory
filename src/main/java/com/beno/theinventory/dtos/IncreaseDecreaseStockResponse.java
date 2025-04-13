package com.beno.theinventory.dtos;

import lombok.Data;

@Data
public class IncreaseDecreaseStockResponse {
    private String message;
    private String newStockQuantity;
}
