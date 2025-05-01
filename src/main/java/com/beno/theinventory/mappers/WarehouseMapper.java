package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseDTO toWarehouseDTOFromWarehouse(Warehouse warehouse);
    Warehouse toWarehouseFromWarehouseDTO(WarehouseDTO warehouseDTO);
}
