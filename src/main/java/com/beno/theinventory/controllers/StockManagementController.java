package com.beno.theinventory.controllers;


import com.beno.theinventory.dtos.IncreaseDecreaseStockDTO;
import com.beno.theinventory.services.StockManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class StockManagementController {

    private final StockManagementService stockManagementService;

    @GetMapping("/{warehouseId}/inventory")
    public ResponseEntity<?> getStockForAllProductsInWarehouse(@PathVariable Integer warehouseId) {
        return new ResponseEntity<>(stockManagementService.getStockForAllProductsInWarehouse(warehouseId), HttpStatus.OK);
    }

    @GetMapping("/{warehouseId}/inventory/{productId}")
    public ResponseEntity<?> getStockForProductInWarehouse(@PathVariable Integer warehouseId, @PathVariable UUID productId) {
        return new ResponseEntity<>(stockManagementService.getStockForProductInWarehouse(warehouseId, productId), HttpStatus.OK);
    }

    @PostMapping("/{warehouseId}/inventory/{productId}/increase")
    public ResponseEntity<?> increaseStock(@PathVariable Integer warehouseId, @PathVariable UUID productId, @RequestBody IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        return new ResponseEntity<>(stockManagementService.increaseStock(warehouseId, productId, increaseDecreaseStockDTO), HttpStatus.OK);
    }

    @PostMapping("/{warehouseId}/inventory/{productId}/decrease")
    public ResponseEntity<?> decreaseStock(@PathVariable Integer warehouseId, @PathVariable UUID productId, @RequestBody IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        return new ResponseEntity<>(stockManagementService.decreaseStock(warehouseId, productId, increaseDecreaseStockDTO), HttpStatus.OK);
    }

    @PostMapping("/{warehouseId}/inventory/{productId}/transfer")
    public ResponseEntity<?> transferStock(@PathVariable Integer warehouseId, @PathVariable UUID productId, @RequestBody IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        return new ResponseEntity<>(stockManagementService.transferStock(warehouseId, productId, increaseDecreaseStockDTO), HttpStatus.OK);
    }
}
