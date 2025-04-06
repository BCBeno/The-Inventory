package com.beno.theinventory.services;

import com.beno.theinventory.dtos.InventoryDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ProductService {

    Map<String, Object> createProduct(InventoryDTO inventoryDTO, Integer warehouseId);

    List<InventoryDTO> getAllProducts(Integer warehouseId);
    InventoryDTO getProductById(Integer warehouseId, UUID id);


    Map<String,String> updateProductPatch(Integer warehouseId, UUID id, InventoryDTO inventoryDTO);

    Map<String, String> updateProductPut(Integer warehouseId, UUID id, InventoryDTO inventoryDTO);

    Map<String, String> deleteProduct(Integer warehouseId, UUID id);
}
