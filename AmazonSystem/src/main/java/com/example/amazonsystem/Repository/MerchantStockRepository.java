package com.example.amazonsystem.Repository;

import com.example.amazonsystem.Model.MerchantStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MerchantStockRepository extends JpaRepository<MerchantStock, Integer> {
}
