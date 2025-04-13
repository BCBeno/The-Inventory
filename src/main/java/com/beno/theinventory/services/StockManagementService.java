package com.beno.theinventory.services;


import com.beno.theinventory.dtos.IncreaseDecreaseStockDTO;
import com.beno.theinventory.dtos.IncreaseDecreaseStockResponse;
import com.beno.theinventory.dtos.StockManagementDTO;

import java.util.List;
import java.util.UUID;

public interface StockManagementService {
    List<StockManagementDTO> getStockForAllProductsInWarehouse(Integer warehouseId);

    StockManagementDTO getStockForProductInWarehouse(Integer warehouseId, UUID productId);

    IncreaseDecreaseStockResponse increaseStock(Integer warehouseId, UUID productId, IncreaseDecreaseStockDTO increaseDecreaseStockDTO);

    IncreaseDecreaseStockResponse decreaseStock(Integer warehouseId, UUID productId, IncreaseDecreaseStockDTO increaseDecreaseStockDTO);

    IncreaseDecreaseStockResponse transferStock(Integer warehouseId, UUID productId, IncreaseDecreaseStockDTO increaseDecreaseStockDTO);
}
