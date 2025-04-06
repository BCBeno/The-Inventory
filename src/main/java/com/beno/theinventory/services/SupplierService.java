package com.beno.theinventory.services;

import com.beno.theinventory.dtos.SupplierDTO;

import java.util.List;
import java.util.Map;

public interface SupplierService {
    public Map<String, Object> createSupplier(SupplierDTO supplierDTO);
    public List<SupplierDTO> getAllSuppliers();
    public SupplierDTO getSupplierById(int id);
    public void updateSupplier(int id, SupplierDTO supplierDTO);
    public void updatePutSupplier(int id, SupplierDTO supplierDTO);
}
