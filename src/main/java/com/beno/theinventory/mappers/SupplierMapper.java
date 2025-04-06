package com.beno.theinventory.mappers;

import com.beno.theinventory.dtos.SupplierDTO;
import com.beno.theinventory.entities.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mapping(source = "id", target = "id")
    public SupplierDTO toSupplierDTOfromSupplier(Supplier supplier);
    @Mapping(source = "id", target = "id")
    public Supplier toSupplierFromSupplierDTO(SupplierDTO supplierDTO);
}
