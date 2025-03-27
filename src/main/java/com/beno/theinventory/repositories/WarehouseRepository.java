package com.beno.theinventory.repositories;

import com.beno.theinventory.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Optional<Warehouse> findByName(String name);
    ArrayList<Warehouse> findAll();
    Optional<Warehouse> findById(int id);

}
