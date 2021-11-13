package com.cjss.accountservice.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;
    @Test
    public void generateToken() {
        //System.out.println(authenticationService.GenerateToken("satya@cjss.com"));
    }

    @Test
    public void validateToken() {
        System.out.println("TEST: "+authenticationService.validToken(
                authenticationService.GenerateToken("satya@cjss.com")));

    }

    @Test
    public void getTokenDetails() {
    }
}