package com.beno.theinventory.services.imp;

import com.beno.theinventory.dtos.OperationResponseDTO;
import com.beno.theinventory.dtos.SupplierDTO;
import com.beno.theinventory.entities.Supplier;
import com.beno.theinventory.exceptions.SupplierNotFoundException;
import com.beno.theinventory.mappers.SupplierMapper;
import com.beno.theinventory.repositories.SupplierRepository;
import com.beno.theinventory.services.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log
public class SupplierServiceImp implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public Map<String, Object> createSupplier(SupplierDTO supplierDTO) {
        if (supplierDTO.getContactEmail() == null || supplierDTO.getContactEmail().isEmpty()) {
            throw new IllegalArgumentException("Contact email cannot be null or empty");
        }
        Supplier supplier = supplierMapper.toSupplierFromSupplierDTO(supplierDTO);

        supplierRepository.save(supplier);
        return Map.of("message", "Supplier created successfully", "id", supplier.getId());
    }

    @Override
    public SupplierDTO getSupplierById(int id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() ->  new SupplierNotFoundException("Supplier not found"));
        return supplierMapper.toSupplierDTOfromSupplier(supplier);
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(supplierMapper::toSupplierDTOfromSupplier)
                .toList();
    }

    @Override
    public OperationResponseDTO updateSupplier(int id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
        if (supplierDTO.getName() != null) {
            supplier.setName(supplierDTO.getName());
        }
        if (supplierDTO.getContactEmail() != null) {
            supplier.setContactEmail(supplierDTO.getContactEmail());
        }
        supplierRepository.save(supplier);
        return new OperationResponseDTO("Supplier updated successfully");
    }

    @Override
    public OperationResponseDTO updatePutSupplier(int id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException("Supplier not found"));
        if (supplierDTO.getName() != null) {
            supplier.setName(supplierDTO.getName());
        }
        if (supplierDTO.getContactEmail() != null) {
            supplier.setContactEmail(supplierDTO.getContactEmail());
        }
        supplierRepository.save(supplier);
        return new OperationResponseDTO("Supplier updated successfully");
    }
}
