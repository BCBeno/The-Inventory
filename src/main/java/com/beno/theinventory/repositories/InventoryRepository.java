package com.beno.theinventory.repositories;

import com.beno.theinventory.entities.Inventory;
import com.beno.theinventory.entities.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    Optional<List<Inventory>> findAllByWarehouseId(Integer warehouseId);
    Optional<Inventory> findById(InventoryId id);
}
