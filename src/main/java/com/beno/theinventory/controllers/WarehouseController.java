package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.WarehouseDTO;

import com.beno.theinventory.services.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

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

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable int id, @RequestBody WarehouseDTO warehouseDTO){
        warehouseService.updateWarehouse(id, warehouseDTO);
        LinkedHashMap<String, String> response = new LinkedHashMap<>();
        response.put("message", "Warehouse updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePutWarehouse(@PathVariable int id, @RequestBody WarehouseDTO warehouseDTO){
        warehouseService.updatePutWarehouse(id, warehouseDTO);
        LinkedHashMap<String, String> response = new LinkedHashMap<>();
        response.put("message", "Warehouse updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
