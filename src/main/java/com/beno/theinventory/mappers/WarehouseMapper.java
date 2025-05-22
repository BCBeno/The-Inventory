package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.WarehouseDTO;
import com.beno.theinventory.entities.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    WarehouseDTO toWarehouseDTOFromWarehouse(Warehouse warehouse);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "location", source = "location")
    Warehouse toWarehouseFromWarehouseDTO(WarehouseDTO warehouseDTO);
}