package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    @Mapping(source = "id", target = "id")
    WarehouseDTO toWarehouseDTOFromWarehouse(Warehouse warehouse);

    @Mapping(source = "id", target = "id")
    Warehouse toWarehouseFromWarehouseDTO(WarehouseDTO warehouseDTO);
}

