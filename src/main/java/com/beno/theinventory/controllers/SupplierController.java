package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.SupplierDTO;
import com.beno.theinventory.services.SupplierService;
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

    @PostMapping()
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return new ResponseEntity<>(supplierService.createSupplier(supplierDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable int id) {
        return new ResponseEntity<>(supplierService.getSupplierById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable int id, @RequestBody SupplierDTO supplierDTO) {
        supplierService.updateSupplier(id, supplierDTO);
        return new ResponseEntity<>(Map.of("message", "Supplier updated successfully"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePutSupplier(@PathVariable int id, @RequestBody SupplierDTO supplierDTO) {
        supplierService.updatePutSupplier(id, supplierDTO);
        return new ResponseEntity<>(Map.of("message", "Supplier updated successfully"), HttpStatus.OK);
    }
}
