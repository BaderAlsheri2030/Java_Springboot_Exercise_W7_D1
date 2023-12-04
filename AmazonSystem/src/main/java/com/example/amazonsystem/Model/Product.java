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
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Product name cannot be empty")
    @Size(min = 4, message = "Product name must be more than 3 length long")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull(message = "Price cannot be empty")
    @Positive(message = "price must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName ="id")
    private Category category;

}
