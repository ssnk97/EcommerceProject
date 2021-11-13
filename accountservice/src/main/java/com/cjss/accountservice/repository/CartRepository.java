package com.cjss.accountservice.repository;

import com.cjss.accountservice.entity.CartEntity;
import com.cjss.accountservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByCustomerEntity(CustomerEntity customerEntity);
}
