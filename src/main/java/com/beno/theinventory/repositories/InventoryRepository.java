package com.beno.theinventory.repositories;

import com.beno.theinventory.entities.Inventory;
import com.beno.theinventory.entities.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
}
