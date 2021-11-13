package com.cjss.accountservice.repository;

import com.cjss.accountservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByEmail(String email);
}
