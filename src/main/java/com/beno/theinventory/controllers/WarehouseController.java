package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.OperationResponseDTO;
import com.beno.theinventory.dtos.WarehouseDTO;

import com.beno.theinventory.services.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Create a new warehouse")
    @PostMapping()
    public ResponseEntity<?> createWarehouse(@RequestBody WarehouseDTO warehouseDTO) {
        return new ResponseEntity<>(warehouseService.createWarehouse(warehouseDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all warehouses")
    @GetMapping()
    public ResponseEntity<?> getAllWarehouses() {
        return new ResponseEntity<>(warehouseService.getAllWarehouses(), HttpStatus.OK);
    }

    @Operation(summary = "Get a warehouse by ID")
    @GetMapping("/{warehouseId}")
    public ResponseEntity<?> getWarehouseById(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )

            @PathVariable int warehouseId) {
        return new ResponseEntity<>(warehouseService.getWarehouseById(warehouseId), HttpStatus.OK);
    }

    @Operation(summary = "Update a warehouse / property of a warehouse by ID")
    @PatchMapping("/{warehouseId}")
    public ResponseEntity<?> updateWarehouse(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable int warehouseId, @RequestBody WarehouseDTO warehouseDTO){
        OperationResponseDTO response = warehouseService.updateWarehouse(warehouseId, warehouseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update a warehouse by ID")
    @PutMapping("/{warehouseId}")
    public ResponseEntity<?> updatePutWarehouse(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )

            @PathVariable int warehouseId, @RequestBody WarehouseDTO warehouseDTO){
        OperationResponseDTO response = warehouseService.updatePutWarehouse(warehouseId, warehouseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
