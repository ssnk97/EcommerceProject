package com.cjss.accountservice.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JasyptServiceTest {
    @InjectMocks
    JasyptService jasyptService;

    @Test
    public void encryptPassword() {
        System.out.println(jasyptService.encryptPassword("password"));

    }
}