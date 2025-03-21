package com.beno.theinventory.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class InventoryId implements Serializable {
    private int warehouseId;
    private int productId;

    public InventoryId(int warehouseId, int productId) {
        this.warehouseId = warehouseId;
        this.productId = productId;
    }
}
