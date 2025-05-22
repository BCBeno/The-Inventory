package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.SupplierDTO;
import com.beno.theinventory.entities.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "contactEmail", source = "contactEmail")
    SupplierDTO toSupplierDTOfromSupplier(Supplier supplier);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "contactEmail", source = "contactEmail")
    Supplier toSupplierFromSupplierDTO(SupplierDTO supplierDTO);
}