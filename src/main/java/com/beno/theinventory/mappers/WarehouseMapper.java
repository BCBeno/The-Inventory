package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    @Mapping(target = "id", source = "id")
    WarehouseDTO toWarehouseDTOFromWarehouse(Warehouse warehouse);
    @Mapping(target = "id", source = "id")
    Warehouse toWarehouseFromWarehouseDTO(WarehouseDTO warehouseDTO);
}
