package com.example.amazonsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(columnDefinition = "not null")
    private Integer categoryID;
    @NotNull(message = "Product name cannot be empty")
    @Size(min = 4, message = "Product name must be more than 3 length long")
    @Column
    private String name;
    @NotNull(message = "Price cannot be empty")
    @Positive(message = "price must be a positive number")
    @Column
    private double price;


}
