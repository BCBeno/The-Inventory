package com.beno.theinventory.services;

import com.beno.theinventory.dtos.OperationResponseDTO;
import com.beno.theinventory.dtos.WarehouseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

public interface WarehouseService {
    Map<String, Object> createWarehouse(WarehouseDTO warehouseDTO);
    ArrayList<WarehouseDTO> getAllWarehouses();
    WarehouseDTO getWarehouseById(int id);
    OperationResponseDTO updateWarehouse(int id, WarehouseDTO warehouseDTO);
    OperationResponseDTO updatePutWarehouse(int id, WarehouseDTO warehouseDTO);
}
