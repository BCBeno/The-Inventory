package com.beno.theinventory.dtos;

import com.beno.theinventory.views.InventoryViews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    @JsonView(InventoryViews.Summary.class)
    private UUID id;
    @JsonView(InventoryViews.Summary.class)
    private String name;
    @JsonView(InventoryViews.Summary.class)
    private String sku;
    @JsonView(InventoryViews.Detailed.class)
    private String description;
    @JsonView(InventoryViews.Summary.class)
    private double price;
    @JsonView(InventoryViews.Detailed.class)
    private String category;
    @JsonView(InventoryViews.Summary.class)
    private int stockQuantity;
}
