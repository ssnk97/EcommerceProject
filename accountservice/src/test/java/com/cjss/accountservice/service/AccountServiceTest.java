package com.cjss.accountservice.service;


import com.cjss.accountservice.model.CustomerModel;
import com.cjss.accountservice.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import com.cjss.accountservice.TestData.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @InjectMocks
    AccountService accountService;
    @Mock
    CustomerRepository customerRepository;

    @Test
    public void login() {
        CustomerModel customerModel = TestDetails.getCustomerModelData();
        when(customerRepository.findByEmail("satya@cjss.com")).thenReturn(TestDetails.getCustomerEntityData());
       assertEquals(accountService.login("satya@cjss.com", "password"),"LOGIN_SUCCESS");
       assertEquals(accountService.login("satya@cjss.com", "passwor"),"INVALID_LOGIN");
    }

    @Test
    public void logout() {
//        accountService.userEmail="";
//        assertEquals(accountService.logout(), "There is no login!");
//        accountService.userEmail="satya@cjss.com";
//        assertEquals(accountService.logout(),"Logout Success");
    }

    @Test
    public void customerRegistration() {
        assertEquals(accountService.customerRegistration(TestDetails.getCustomerModelData()),
                ResponseEntity.status(HttpStatus.CREATED).body("REGISTRATION SUCCESSFUL\n"));
        assertEquals(accountService.customerRegistration(null),
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body cannot be empty\n"));

        when(customerRepository.findByEmail("satya@cjss.com")).thenReturn(TestDetails.getCustomerEntityData());
        assertEquals(accountService.customerRegistration(TestDetails.getCustomerModelData()),
                ResponseEntity.status(HttpStatus.CREATED).body("REGISTRATION SUCCESSFUL\n"));

    }


    @Test
    public void addAddress() {
//        accountService.userEmail ="";
//        assertEquals(accountService.addAddress(TestDetails.getAddressModelData()),
//                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please Login\n"));
//
//        accountService.userEmail ="satya@cjss.com";
//        when(customerRepository.findByEmail("satya@cjss.com")).thenReturn(TestDetails.getCustomerEntityData());
//        assertEquals(accountService.addAddress(TestDetails.getAddressModelData()),
//                ResponseEntity.status(HttpStatus.CREATED).body("Address added Successfully (CODE 200)\n"));
      }

    @Test
    public void getCustomerDetails() {
//        accountService.userEmail ="";
//        assertEquals(accountService.addAddress(TestDetails.getAddressModelData()),
//                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Please Login\n"));
    }
}