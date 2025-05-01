package com.beno.theinventory.repositories;

import com.beno.theinventory.entities.InventoryMovement;
import com.beno.theinventory.entities.Supplier;
import com.beno.theinventory.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Integer> {
    ArrayList<InventoryMovement> findAllBySourceWarehouse(Warehouse sourceWarehouse);
    ArrayList<InventoryMovement> findAllBySupplier(Supplier supplierId);
}
