package com.example.amazonsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Merchant Name cannot be empty")
    @Size(min = 4 , message = "Merchant name must be more than 3 length long")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

}
