package com.beno.theinventory.repositories;

import com.beno.theinventory.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Optional<Supplier> findByName(String name);
    Optional<Supplier> findById(int id);
}
