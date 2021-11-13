package com.cjss.accountservice.controller;

import com.cjss.accountservice.service.AuthenticationService;
import com.cjss.accountservice.service.ReturnService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController {
    @Autowired
    ReturnService returnService;
    @PatchMapping("/return-item/{itemId}")
    public ResponseEntity<String> returnItem(@PathVariable Long itemId, @RequestHeader String encryptedToken){
        AuthenticationService authenticationService = new AuthenticationService();
        if(authenticationService.validToken(encryptedToken)) {
            String message = returnService.returnItem(itemId, authenticationService.getTokenDetails(encryptedToken));
            if(message.contains("RETURN ACCEPTED"))
                return ResponseEntity.status(HttpStatus.OK).body(message);
            if(message.equals("INVALID ITEM ID"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            if(message.equals("EMAIL NOT FOUND"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            else
                return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");

        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID TOKEN (CODE 401)");
    }
}
