package com.beno.theinventory.dtos;

import lombok.Data;

@Data
public class IncreaseDecreaseStockDTO {
    private int quantity;
    private int supplierId;
    private int targetWarehouseId;
    private String reason;
}
