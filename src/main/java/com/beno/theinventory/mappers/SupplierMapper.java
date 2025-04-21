package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.SupplierDTO;
import com.beno.theinventory.entities.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mapping(target = "id", source = "id")
    public SupplierDTO toSupplierDTOfromSupplier(Supplier supplier);
    @Mapping(target = "id", source = "id")
    public Supplier toSupplierFromSupplierDTO(SupplierDTO supplierDTO);
}
