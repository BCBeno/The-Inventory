package com.beno.theinventory.entities;

import com.beno.theinventory.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "inventory_movements")
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "source_warehouse_id", nullable = true)
    private Warehouse sourceWarehouse; // Used for stock transfers

    @ManyToOne
    @JoinColumn(name = "destination_warehouse_id", nullable = true)
    private Warehouse destinationWarehouse; // Used for stock-in & transfers

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = true)
    private Supplier supplier; // ✅ Only used for STOCK_IN movements

    @Column(nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType movementType;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
