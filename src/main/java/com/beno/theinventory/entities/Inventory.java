package com.beno.theinventory.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @EmbeddedId
    private InventoryId id;

    @ManyToOne
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    public Inventory(Warehouse warehouse, Product product, int quantity) {
        if (warehouse == null || product == null) {
            throw new IllegalArgumentException("Warehouse and Product cannot be null");
        }

        this.warehouse = warehouse;
        this.product = product;
        this.quantity = quantity;

        this.id = new InventoryId(warehouse.getId(), product.getId());
    }

}
