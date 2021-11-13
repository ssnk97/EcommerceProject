package com.cjss.accountservice.repository;

import com.cjss.accountservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductCode(String productCode);
}
