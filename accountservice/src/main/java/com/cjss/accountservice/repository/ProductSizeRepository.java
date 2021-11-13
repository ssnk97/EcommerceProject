package com.cjss.accountservice.repository;

import com.cjss.accountservice.entity.ProductPriceEntity;
import com.cjss.accountservice.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, String> {
    ProductSizeEntity findBySkuCode(String skuCode);
}
