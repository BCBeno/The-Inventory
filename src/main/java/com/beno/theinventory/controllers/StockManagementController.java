package com.beno.theinventory.controllers;


import com.beno.theinventory.dtos.IncreaseDecreaseStockDTO;
import com.beno.theinventory.services.StockManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/warehouses/{warehouseId}/inventory")
@RequiredArgsConstructor
public class StockManagementController {

    private final StockManagementService stockManagementService;

    @Operation(summary = "Get stock for all products in a warehouse")
    @GetMapping("/")
    public ResponseEntity<?> getStockForAllProductsInWarehouse(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId) {
        return new ResponseEntity<>(stockManagementService.getStockForAllProductsInWarehouse(warehouseId), HttpStatus.OK);
    }

    @Operation(summary = "Get stock for a specific product in a warehouse")
    @GetMapping("/{productId}")
    public ResponseEntity<?> getStockForProductInWarehouse(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId,
            @Parameter(
                    name = "productId",
                    description = "Unique product identifier",
                    example = "123e4567-e89b-12d3-a456-426614174000",
                    required = true
            )
            @PathVariable UUID productId) {
        return new ResponseEntity<>(stockManagementService.getStockForProductInWarehouse(warehouseId, productId), HttpStatus.OK);
    }

    @Operation(summary = "Increase stock for a specific product in a warehouse")
    @PostMapping("/{productId}/increase")
    public ResponseEntity<?> increaseStock(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId,
            @Parameter(
                    name = "productId",
                    description = "Unique product identifier",
                    example = "123e4567-e89b-12d3-a456-426614174000",
                    required = true
            )
            @PathVariable UUID productId,
            @RequestBody IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        return new ResponseEntity<>(stockManagementService.increaseStock(warehouseId, productId, increaseDecreaseStockDTO), HttpStatus.OK);
    }

    @Operation(summary = "Decrease stock for a specific product in a warehouse")
    @PostMapping("/{productId}/decrease")
    public ResponseEntity<?> decreaseStock(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId,
            @Parameter(
                    name = "productId",
                    description = "Unique product identifier",
                    example = "123e4567-e89b-12d3-a456-426614174000",
                    required = true
            )
            @PathVariable UUID productId, @RequestBody IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        return new ResponseEntity<>(stockManagementService.decreaseStock(warehouseId, productId, increaseDecreaseStockDTO), HttpStatus.OK);
    }

    @Operation(summary = "Transfer stock for a specific product in a warehouse")
    @PostMapping("/{productId}/transfer")
    public ResponseEntity<?> transferStock(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId,
            @Parameter(
                    name = "productId",
                    description = "Unique product identifier",
                    example = "123e4567-e89b-12d3-a456-426614174000",
                    required = true
            )
            @PathVariable UUID productId, @RequestBody IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        return new ResponseEntity<>(stockManagementService.transferStock(warehouseId, productId, increaseDecreaseStockDTO), HttpStatus.OK);
    }
}
