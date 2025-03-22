package com.beno.theinventory;

import com.beno.theinventory.exceptions.WarehouseNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheInventoryApplication.class, args);
    }

}
