package com.beno.theinventory.services.imp;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import com.beno.theinventory.mappers.WarehouseMapper;
import com.beno.theinventory.repositories.WarehouseRepository;
import com.beno.theinventory.services.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log
@RequiredArgsConstructor
public class WarehouseServiceImp implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    @Autowired
    private final WarehouseMapper warehouseMapper;

 /*   public WarehouseServiceImp(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }*/

    @Override
    public Map<String, Object> createWarehouse(WarehouseDTO warehouseDTO){
        Warehouse warehouse = warehouseMapper.toWarehouseFromWarehouseDTO(warehouseDTO);
        warehouseRepository.save(warehouse);
        return Map.of(
                "message", "Warehouse created",
                "id", warehouse.getId());
    }
}
