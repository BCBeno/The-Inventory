package com.beno.theinventory.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class InventoryId implements Serializable {
    private int warehouseId;
    private UUID productId;

    public InventoryId(int warehouseId, UUID productId) {
        this.warehouseId = warehouseId;
        this.productId = productId;
    }
}
