package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.exceptions.WarehouseNotFoundException;

import com.beno.theinventory.services.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    @Autowired
    private final WarehouseService warehouseService;

/*    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }*/

    @PostMapping("")
    public ResponseEntity<?> createWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        return new ResponseEntity<>(warehouseService.createWarehouse(warehouseDTO), HttpStatus.CREATED);
    }

}
