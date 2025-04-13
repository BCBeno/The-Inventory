package com.beno.theinventory.dtos;

import lombok.Data;

import java.rmi.server.UID;
import java.util.UUID;

@Data
public class StockManagementDTO {
    private UUID productId;
    private String sku;
    private int stockQuantity;

}
