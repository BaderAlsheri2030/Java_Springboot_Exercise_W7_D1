package com.example.amazonsystem.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;
    @OneToOne
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchantId;
    @NotNull(message = "Stock cannot be empty")
    @Min(value = 10, message = "Stock has to be more than 10 at start")
    @Column(columnDefinition = "int not null")
    private Integer stock;
}
