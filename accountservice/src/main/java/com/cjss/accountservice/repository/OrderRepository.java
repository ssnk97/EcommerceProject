package com.cjss.accountservice.repository;

import com.cjss.accountservice.entity.CustomerEntity;
import com.cjss.accountservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByCustomerEntity(Optional<CustomerEntity> customerEntity);
}
