package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.InventoryDTO;
import com.beno.theinventory.services.ProductService;
import com.beno.theinventory.views.InventoryViews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/{warehouseId}/products")
    public ResponseEntity<?> createProduct(@PathVariable Integer warehouseId, @RequestBody InventoryDTO inventoryDTO) {;
        return new ResponseEntity<>(productService.createProduct(inventoryDTO, warehouseId), HttpStatus.CREATED);
    }

    @JsonView(InventoryViews.Summary.class)
    @GetMapping("/{warehouseId}/products")
    public ResponseEntity<?> getAllProducts(@PathVariable Integer warehouseId) {
        return new ResponseEntity<>(productService.getAllProducts(warehouseId), HttpStatus.OK);
    }

    @JsonView(InventoryViews.Detailed.class)
    @GetMapping("/{warehouseId}/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer warehouseId, @PathVariable UUID id) {
        return new ResponseEntity<>(productService.getProductById(warehouseId, id), HttpStatus.OK);
    }

    @PatchMapping("/{warehouseId}/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer warehouseId, @PathVariable UUID id, @RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(productService.updateProductPatch(warehouseId, id, inventoryDTO), HttpStatus.OK);
    }

    @PutMapping("/{warehouseId}/products/{id}")
    public ResponseEntity<?> updateProductPut(@PathVariable Integer warehouseId, @PathVariable UUID id, @RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(productService.updateProductPut(warehouseId, id, inventoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{warehouseId}/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer warehouseId, @PathVariable UUID id) {
        return new ResponseEntity<>(productService.deleteProduct(warehouseId, id), HttpStatus.OK);
    }
}
