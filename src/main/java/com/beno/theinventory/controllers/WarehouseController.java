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
    private final WarehouseService warehouseService;

    @PostMapping()
    public ResponseEntity<?> createWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        return new ResponseEntity<>(warehouseService.createWarehouse(warehouseDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllWarehouses() {
        return new ResponseEntity<>(warehouseService.getAllWarehouses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable int id) {
        return new ResponseEntity<>(warehouseService.getWarehouseById(id), HttpStatus.OK);
    }

}
