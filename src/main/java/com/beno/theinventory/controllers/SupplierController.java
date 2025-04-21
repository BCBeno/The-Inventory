package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.OperationResponseDTO;
import com.beno.theinventory.dtos.SupplierDTO;
import com.beno.theinventory.services.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @Operation(summary = "Create a new supplier")
    @PostMapping()
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return new ResponseEntity<>(supplierService.createSupplier(supplierDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all suppliers")
    @GetMapping()
    public ResponseEntity<?> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @Operation(summary = "Get a supplier by ID")
    @GetMapping("/{supplierId}")
    public ResponseEntity<?> getSupplierById(
            @Parameter(
                    name = "supplierId",
                    description = "Unique supplier identifier",
                    example = "42",
                    required = true
            )
            @PathVariable int supplierId) {
        return new ResponseEntity<>(supplierService.getSupplierById(supplierId), HttpStatus.OK);
    }

    @Operation(summary = "Update a supplier / property of a supplier by ID")
    @PatchMapping("/{supplierId}")
    public ResponseEntity<?> updateSupplier(
            @Parameter(
                    name = "supplierId",
                    description = "Unique supplier identifier",
                    example = "42",
                    required = true
            )
            @PathVariable int supplierId, @RequestBody SupplierDTO supplierDTO) {
        OperationResponseDTO response = supplierService.updateSupplier(supplierId, supplierDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update a supplier by ID")
    @PutMapping("/{supplierId}")
    public ResponseEntity<?> updatePutSupplier(
            @Parameter(
                    name = "supplierId",
                    description = "Unique supplier identifier",
                    example = "42",
                    required = true
            )
            @PathVariable int supplierId, @RequestBody SupplierDTO supplierDTO) {
        OperationResponseDTO response = supplierService.updatePutSupplier(supplierId, supplierDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
