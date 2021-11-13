package com.cjss.accountservice.controller;


import com.cjss.accountservice.model.AddressModel;
import com.cjss.accountservice.model.CustomerModel;
import com.cjss.accountservice.model.LoginModel;
import com.cjss.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public ResponseEntity<String> customerRegistration(@Valid  @RequestBody CustomerModel customerModel){
        return accountService.customerRegistration(customerModel);

    }

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginModel loginModel)  {
        String userEmail = loginModel.getUserEmail();
        String password = loginModel.getPassword();
        return accountService.login(userEmail, password);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(@RequestHeader String encryptedToken){
        return accountService.logout(encryptedToken);
    }


    @PatchMapping("/add-address")
    public ResponseEntity<String> addAddress(@Valid @RequestBody AddressModel addressModel, @RequestHeader String encryptedToken){
        return accountService.addAddress(addressModel, encryptedToken);
    }


    @GetMapping("/get-customer-details")
    public ResponseEntity<String> getCustomerDetails(@RequestHeader String encryptedToken){
        return accountService.getCustomerDetails(encryptedToken);

    }

    @RequestMapping(value ="/get-all-customers", method = RequestMethod.GET)
    public List<CustomerModel> getAllCustomers(){
        return accountService.getAllCustomers();
    }
}
