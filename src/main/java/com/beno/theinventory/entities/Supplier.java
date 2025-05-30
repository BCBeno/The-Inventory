package com.beno.theinventory.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "supplier")
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String contactEmail;
}
