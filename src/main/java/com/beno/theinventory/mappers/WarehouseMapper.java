package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse toWarehouseFromWarehouseDTO(WarehouseDTO warehouseDTO);
    WarehouseDTO toWarehouseDTOFromWarehouse(Warehouse warehouse);
}

