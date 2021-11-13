package com.cjss.accountservice.service;


import com.cjss.accountservice.repository.CustomerRepository;
import com.cjss.accountservice.repository.InventoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @InjectMocks
    CartService cartService;
    @Mock
    InventoryRepository inventoryRepository;
    @Mock
    CustomerRepository customerRepository;

    @Test
    public void addToCart() {
    }
}