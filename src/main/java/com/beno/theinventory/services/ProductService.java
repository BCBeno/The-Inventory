package com.beno.theinventory.services;

import com.beno.theinventory.dtos.InventoryDTO;
import com.beno.theinventory.dtos.OperationResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProductService {

    Map<String, Object> createProduct(InventoryDTO inventoryDTO, Integer warehouseId);

    List<InventoryDTO> getAllProducts(Integer warehouseId);
    InventoryDTO getProductById(Integer warehouseId, UUID id);


    OperationResponseDTO updateProductPatch(Integer warehouseId, UUID id, InventoryDTO inventoryDTO);

    OperationResponseDTO updateProductPut(Integer warehouseId, UUID id, InventoryDTO inventoryDTO);

    OperationResponseDTO deleteProduct(Integer warehouseId, UUID id);
}
