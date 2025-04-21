package com.beno.theinventory.services.imp;

import com.beno.theinventory.dtos.OperationResponseDTO;
import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import com.beno.theinventory.exceptions.WarehouseNotFoundException;
import com.beno.theinventory.mappers.WarehouseMapper;
import com.beno.theinventory.repositories.WarehouseRepository;
import com.beno.theinventory.services.WarehouseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImp implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;


    @Override
    public Map<String, Object> createWarehouse(WarehouseDTO warehouseDTO){
        Warehouse warehouse = warehouseMapper.toWarehouseFromWarehouseDTO(warehouseDTO);
        warehouseRepository.save(warehouse);
        return Map.of(
                "message", "Warehouse created",
                "id", warehouse.getId());
    }

    @Override
    public ArrayList<WarehouseDTO> getAllWarehouses(){
        ArrayList<Warehouse> warehouses = warehouseRepository.findAll();
        ArrayList<WarehouseDTO> warehouseDTOS = new ArrayList<>();
        warehouses.forEach(warehouse -> {
            warehouseDTOS.add(warehouseMapper.toWarehouseDTOFromWarehouse(warehouse));
        });
        return warehouseDTOS;
    }

    @Override
    public WarehouseDTO getWarehouseById(int id){
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
        return warehouseMapper.toWarehouseDTOFromWarehouse(warehouse);
    }

    @Override
    @Transactional
    public OperationResponseDTO updateWarehouse(int id, WarehouseDTO warehouseDTO){
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
        WarehouseDTO warehouseDTOdata = warehouseMapper.toWarehouseDTOFromWarehouse(warehouse);
        if(warehouseDTO.getName() != null){
            warehouseDTOdata.setName(warehouseDTO.getName());
        }
        if(warehouseDTO.getLocation() != null){
            warehouseDTOdata.setLocation(warehouseDTO.getLocation());
        }
        warehouse = warehouseMapper.toWarehouseFromWarehouseDTO(warehouseDTOdata);
        warehouseRepository.save(warehouse);
        return new OperationResponseDTO("Warehouse updated successfully");
    }

    @Override
    @Transactional
    public OperationResponseDTO updatePutWarehouse(int id, WarehouseDTO warehouseDTO){
        Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
        WarehouseDTO warehouseDTOdata = warehouseMapper.toWarehouseDTOFromWarehouse(warehouse);
        if(warehouseDTO.getName() == null){
            throw new WarehouseNotFoundException("Name is required");
        }
        if(warehouseDTO.getLocation() == null){
            throw new WarehouseNotFoundException("Location is required");
        }
        warehouse = warehouseMapper.toWarehouseFromWarehouseDTO(warehouseDTO);
        warehouse.setId(id);
        warehouseRepository.save(warehouse);
        return new OperationResponseDTO("Warehouse updated successfully");
    }
}
