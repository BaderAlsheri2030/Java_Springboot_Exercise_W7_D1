package com.example.amazonsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Product ID cannot be empty")
    @Column
    private Integer productId;
    @NotEmpty(message = "Merchant ID cannot be empty")
    @Column
    private Integer merchantId;
    @NotNull(message = "Stock cannot be empty")
    @Min(value = 10, message = "Stock has to be more than 10 at start")
    @Column
    private Integer stock;
}
