package com.beno.theinventory.services;

import com.beno.theinventory.dtos.WarehouseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

public interface WarehouseService {
    Map<String, Object> createWarehouse(WarehouseDTO warehouseDTO);
    ArrayList<WarehouseDTO> getAllWarehouses();
    WarehouseDTO getWarehouseById(int id);
}
