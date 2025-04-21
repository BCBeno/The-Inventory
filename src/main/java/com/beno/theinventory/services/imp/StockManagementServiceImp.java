package com.beno.theinventory.services.imp;

import com.beno.theinventory.dtos.IncreaseDecreaseStockDTO;
import com.beno.theinventory.dtos.IncreaseDecreaseStockResponse;
import com.beno.theinventory.dtos.StockManagementDTO;
import com.beno.theinventory.entities.Inventory;
import com.beno.theinventory.entities.InventoryId;
import com.beno.theinventory.entities.InventoryMovement;
import com.beno.theinventory.entities.Warehouse;
import com.beno.theinventory.exceptions.InsufficientStockException;
import com.beno.theinventory.exceptions.ProductNotFountException;
import com.beno.theinventory.exceptions.SupplierNotFoundException;
import com.beno.theinventory.exceptions.WarehouseNotFoundException;
import com.beno.theinventory.repositories.InventoryMovementRepository;
import com.beno.theinventory.repositories.InventoryRepository;
import com.beno.theinventory.repositories.SupplierRepository;
import com.beno.theinventory.repositories.WarehouseRepository;
import com.beno.theinventory.services.StockManagementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockManagementServiceImp implements StockManagementService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public List<StockManagementDTO> getStockForAllProductsInWarehouse(Integer warehouseId) {
        List<StockManagementDTO> stockManagementDTOList = new ArrayList<>();
        List<Inventory> inventoryList = inventoryRepository.findAllByWarehouseId(warehouseId).orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
        inventoryList.forEach(inventory -> {
            StockManagementDTO stockManagementDTO = new StockManagementDTO();
            stockManagementDTO.setProductId(inventory.getProduct().getId());
            stockManagementDTO.setSku(inventory.getProduct().getSku());
            stockManagementDTO.setStockQuantity(inventory.getQuantity());
            stockManagementDTOList.add(stockManagementDTO);
        });
        return stockManagementDTOList;
    }

    @Override
    public StockManagementDTO getStockForProductInWarehouse(Integer warehouseId, UUID productId) {
        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, productId)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        StockManagementDTO stockManagementDTO = new StockManagementDTO();
        stockManagementDTO.setProductId(inventory.getProduct().getId());
        stockManagementDTO.setSku(inventory.getProduct().getSku());
        stockManagementDTO.setStockQuantity(inventory.getQuantity());
        return stockManagementDTO;
    }

    @Override
    @Transactional
    public IncreaseDecreaseStockResponse increaseStock(Integer warehouseId, UUID productId, IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        if (supplierRepository.findById(increaseDecreaseStockDTO.getSupplierId()).isEmpty())
            throw new SupplierNotFoundException("Supplier not found");

        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, productId)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        inventory.setQuantity(inventory.getQuantity() + increaseDecreaseStockDTO.getQuantity());
        inventoryRepository.save(inventory);

        InventoryMovement inventoryMovement = new InventoryMovement();
        inventoryMovement.setProduct(inventory.getProduct());
        inventoryMovement.setDestinationWarehouse(inventory.getWarehouse());
        inventoryMovement.setSupplier(supplierRepository.findById(increaseDecreaseStockDTO.getSupplierId()).get());
        inventoryMovement.setQuantity(increaseDecreaseStockDTO.getQuantity());
        inventoryMovement.setTimestamp(java.time.LocalDateTime.now());
        inventoryMovementRepository.save(inventoryMovement);


        IncreaseDecreaseStockResponse increaseDecreaseStockResponse = new IncreaseDecreaseStockResponse();
        increaseDecreaseStockResponse.setMessage("Stock increased successfully");
        increaseDecreaseStockResponse.setNewStockQuantity(Integer.toString(inventory.getQuantity()));

        return increaseDecreaseStockResponse;
    }

    @Override
    @Transactional
    public IncreaseDecreaseStockResponse decreaseStock(Integer warehouseId, UUID productId, IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {

        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, productId)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        if(inventory.getQuantity() < increaseDecreaseStockDTO.getQuantity())
            throw new InsufficientStockException("Insufficient stock to decrease");
        inventory.setQuantity(inventory.getQuantity() - increaseDecreaseStockDTO.getQuantity());
        inventoryRepository.save(inventory);

        InventoryMovement inventoryMovement = new InventoryMovement();
        inventoryMovement.setProduct(inventory.getProduct());
        inventoryMovement.setDestinationWarehouse(inventory.getWarehouse());
        inventoryMovement.setQuantity(-increaseDecreaseStockDTO.getQuantity());
        inventoryMovement.setTimestamp(java.time.LocalDateTime.now());
        inventoryMovement.setReason(increaseDecreaseStockDTO.getReason());
        inventoryMovementRepository.save(inventoryMovement);

        IncreaseDecreaseStockResponse increaseDecreaseStockResponse = new IncreaseDecreaseStockResponse();
        increaseDecreaseStockResponse.setMessage("Stock decreased successfully");
        increaseDecreaseStockResponse.setNewStockQuantity(Integer.toString(inventory.getQuantity()));

        return increaseDecreaseStockResponse;
    }

    @Override
    @Transactional
    public IncreaseDecreaseStockResponse transferStock(Integer warehouseId, UUID productId, IncreaseDecreaseStockDTO increaseDecreaseStockDTO) {
        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, productId)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        if(inventory.getQuantity() < increaseDecreaseStockDTO.getQuantity())
            throw new InsufficientStockException("Insufficient stock to transfer");
        inventory.setQuantity(inventory.getQuantity() - increaseDecreaseStockDTO.getQuantity());
        inventoryRepository.save(inventory);

        Warehouse targetWarehouse = warehouseRepository.findById(increaseDecreaseStockDTO.getTargetWarehouseId()).orElseThrow(() -> new WarehouseNotFoundException("Target warehouse not found"));

        Inventory targetInventory = inventoryRepository.findById(new InventoryId(targetWarehouse.getId(), productId)).orElse(new Inventory(targetWarehouse, inventory.getProduct(), increaseDecreaseStockDTO.getQuantity()));

        inventoryRepository.save(targetInventory);

        InventoryMovement inventoryMovement = new InventoryMovement();
        inventoryMovement.setProduct(inventory.getProduct());
        inventoryMovement.setDestinationWarehouse(targetWarehouse);
        inventoryMovement.setSourceWarehouse(inventory.getWarehouse());
        inventoryMovement.setQuantity(-increaseDecreaseStockDTO.getQuantity());
        inventoryMovement.setTimestamp(java.time.LocalDateTime.now());
        inventoryMovement.setReason(increaseDecreaseStockDTO.getReason());
        inventoryMovementRepository.save(inventoryMovement);

        IncreaseDecreaseStockResponse increaseDecreaseStockResponse = new IncreaseDecreaseStockResponse();
        increaseDecreaseStockResponse.setMessage("Stock transferred successfully");
        increaseDecreaseStockResponse.setNewStockQuantity(Integer.toString(inventory.getQuantity()));

        return increaseDecreaseStockResponse;
    }
}
