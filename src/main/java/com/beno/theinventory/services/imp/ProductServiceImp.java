package com.beno.theinventory.services.imp;

import com.beno.theinventory.dtos.InventoryDTO;
import com.beno.theinventory.entities.Category;
import com.beno.theinventory.entities.Inventory;
import com.beno.theinventory.entities.InventoryId;
import com.beno.theinventory.entities.Product;
import com.beno.theinventory.exceptions.ProductAlreadyExistsException;
import com.beno.theinventory.exceptions.ProductNotFountException;
import com.beno.theinventory.exceptions.StockQuantityEditedInProductException;
import com.beno.theinventory.exceptions.WarehouseNotFoundException;
import com.beno.theinventory.repositories.CategoryRepository;
import com.beno.theinventory.repositories.InventoryRepository;
import com.beno.theinventory.repositories.ProductRepository;
import com.beno.theinventory.repositories.WarehouseRepository;
import com.beno.theinventory.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Map<String, Object> createProduct(InventoryDTO inventoryDTO, Integer warehouseId) {
        if(warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse not found");
        }

        if(productRepository.findProductBySku(inventoryDTO.getSku()).isPresent()) {
            throw new ProductAlreadyExistsException("Product with SKU " + inventoryDTO.getSku() + " already exists");
        }

        if(categoryRepository.findByName(inventoryDTO.getCategory()).isEmpty()) {
            Category newCategory = new Category();
            newCategory.setName(inventoryDTO.getCategory());
            categoryRepository.save(newCategory);
        }

        Product product = new Product(inventoryDTO.getName(), inventoryDTO.getSku(), inventoryDTO.getDescription(), inventoryDTO.getPrice(), categoryRepository.findByName(inventoryDTO.getCategory()).get());
        productRepository.save(product);

        Inventory inventory = new Inventory(warehouseRepository.findById(warehouseId).get(), product, inventoryDTO.getStockQuantity());
        inventoryRepository.save(inventory);


        return Map.of(
                "id", inventory.getProduct().getId(),
                "message", "Product created successfully"
        );
    }

    @Override
    public List<InventoryDTO> getAllProducts(Integer warehouseId) {
        if(warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse not found");
        }
        List<Inventory> inventories = inventoryRepository.findAllByWarehouseId(warehouseId).get();
       return inventories.stream()
                .map(inventory -> new InventoryDTO(
                        inventory.getProduct().getId(),
                        inventory.getProduct().getName(),
                        inventory.getProduct().getSku(),
                        inventory.getProduct().getDescription(),
                        inventory.getProduct().getPrice(),
                        inventory.getProduct().getCategory().getName(),
                        inventory.getQuantity()
                ))
                .toList();

    }

    @Override
    public InventoryDTO getProductById(Integer warehouseId, UUID id) {
        if(warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse not found");
        }
        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, id)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        return new InventoryDTO(
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getProduct().getSku(),
                inventory.getProduct().getDescription(),
                inventory.getProduct().getPrice(),
                inventory.getProduct().getCategory().getName(),
                inventory.getQuantity()
        );
    }

    @Override
    public Map<String, String> updateProductPatch(Integer warehouseId, UUID id, InventoryDTO inventoryDTO) {
        if(warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse not found");
        }
        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, id)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        if(inventoryDTO.getStockQuantity() != inventory.getQuantity() && inventoryDTO.getStockQuantity() != 0)
            throw new StockQuantityEditedInProductException("Stock quantity cannot be edited in product");
        if(inventoryDTO.getName() != null)
            inventory.getProduct().setName(inventoryDTO.getName());
        if(inventoryDTO.getSku() != null)
            inventory.getProduct().setSku(inventoryDTO.getSku());
        if(inventoryDTO.getPrice() != 0)
            inventory.getProduct().setPrice(inventoryDTO.getPrice());
        if(inventoryDTO.getCategory() != null) {
            if(categoryRepository.findByName(inventoryDTO.getCategory()).isEmpty()) {
                Category newCategory = new Category();
                newCategory.setName(inventoryDTO.getCategory());
                categoryRepository.save(newCategory);
            }
            inventory.getProduct().setCategory(categoryRepository.findByName(inventoryDTO.getCategory()).get());
        }
        if(inventoryDTO.getDescription() != null)
            inventory.getProduct().setDescription(inventoryDTO.getDescription());
        inventoryRepository.save(inventory);
        return Map.of(
                "message", "Product updated successfully"
        );
    }

    @PutMapping("/{warehouseId}/products/{id}")
    public Map<String, String> updateProductPut(Integer warehouseId, UUID id, InventoryDTO inventoryDTO) {
        if(warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse not found");
        }
        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, id)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        if(inventoryDTO.getStockQuantity() != inventory.getQuantity())
            throw new StockQuantityEditedInProductException("Cannot modify stock quantity.");
        if(inventoryDTO.getName() != null)
            inventory.getProduct().setName(inventoryDTO.getName());
        if(inventoryDTO.getSku() != null)
            inventory.getProduct().setSku(inventoryDTO.getSku());
        if(inventoryDTO.getPrice() != 0)
            inventory.getProduct().setPrice(inventoryDTO.getPrice());
        if(inventoryDTO.getCategory() != null) {
            if(categoryRepository.findByName(inventoryDTO.getCategory()).isEmpty()) {
                Category newCategory = new Category();
                newCategory.setName(inventoryDTO.getCategory());
                categoryRepository.save(newCategory);
            }
            inventory.getProduct().setCategory(categoryRepository.findByName(inventoryDTO.getCategory()).get());
        }
        if(inventoryDTO.getDescription() != null)
            inventory.getProduct().setDescription(inventoryDTO.getDescription());
        inventoryRepository.save(inventory);
        return Map.of(
                "message", "Product updated successfully"
        );
    }

    @Override
    public Map<String, String> deleteProduct(Integer warehouseId, UUID id) {
        if(warehouseRepository.findById(warehouseId).isEmpty()) {
            throw new WarehouseNotFoundException("Warehouse not found");
        }
        Inventory inventory = inventoryRepository.findById(new InventoryId(warehouseId, id)).orElseThrow(() -> new ProductNotFountException("Product not found"));
        inventoryRepository.delete(inventory);
        return Map.of(
                "message", "Product deleted successfully"
        );
    }

}
