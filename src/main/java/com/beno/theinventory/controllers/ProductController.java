package com.beno.theinventory.controllers;

import com.beno.theinventory.dtos.InventoryDTO;
import com.beno.theinventory.dtos.OperationResponseDTO;
import com.beno.theinventory.services.ProductService;
import com.beno.theinventory.views.InventoryViews;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/warehouses/{warehouseId}/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @Operation(summary = "Create a new product")
    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId, @RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(productService.createProduct(inventoryDTO, warehouseId), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all products in a warehouse")
    @JsonView(InventoryViews.Summary.class)
    @GetMapping("")
    public ResponseEntity<?> getAllProducts(
            @Parameter(
                    name = "warehouseId",
                    description = "Unique warehouse identifier",
                    example = "42",
                    required = true
            )
            @PathVariable Integer warehouseId) {
        return new ResponseEntity<>(productService.getAllProducts(warehouseId), HttpStatus.OK);
    }

    @Operation(summary = "Get a product by ID")
    @JsonView(InventoryViews.Detailed.class)
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(
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
        return new ResponseEntity<>(productService.getProductById(warehouseId, productId), HttpStatus.OK);
    }

    @Operation(summary = "Update a product / property of a product by ID")
    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
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
            @PathVariable UUID productId, @RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(productService.updateProductPatch(warehouseId, productId, inventoryDTO), HttpStatus.OK);
    }

    @Operation(summary = "Update a product by ID")
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProductPut(
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
            @PathVariable UUID productId, @RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(productService.updateProductPut(warehouseId, productId, inventoryDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(
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
        OperationResponseDTO response = productService.deleteProduct(warehouseId, productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
