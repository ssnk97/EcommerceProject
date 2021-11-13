package com.cjss.accountservice.controller;

import com.cjss.accountservice.entity.CartEntity;
import com.cjss.accountservice.entity.CustomerEntity;
import com.cjss.accountservice.model.CartModel;
import com.cjss.accountservice.service.AuthenticationService;
import com.cjss.accountservice.service.CartService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @PatchMapping("/add-cart")
    public ResponseEntity<String> addToCart(@Valid  @RequestBody CartModel cartModel, @RequestHeader String encryptedToken){
        if(encryptedToken.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("TOKEN IS EMPTY \n");
        }
        AuthenticationService authenticationService = new AuthenticationService();
        if(authenticationService.validToken(encryptedToken)){
            String message = cartService.addToCart(cartModel, authenticationService.getTokenDetails(encryptedToken));
            if(message.equals("OK"))
                return ResponseEntity.status(HttpStatus.OK).body("Added Product "+ cartModel.getProductCode()+ "\tQuantity: "+cartModel.getQuantity()+"\nSuccessfully (CODE 200)");
            else if(message.contains("LIMITED STOCK")) return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            else return ResponseEntity.status(HttpStatus.CONFLICT).body("NO MESSAGE");
        }else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID TOKEN (CODE 401)");
    }

    @GetMapping("/view-cart")
    public ResponseEntity<String> viewCart(@RequestHeader String encryptedToken){
        AuthenticationService authenticationService = new AuthenticationService();
        if(authenticationService.validToken(encryptedToken)) {
            String message = cartService.viewCart(authenticationService.getTokenDetails(encryptedToken));
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID TOKEN (CODE 401)");

    }
    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestParam Long addressId, @RequestHeader String encryptedToken){
        AuthenticationService authenticationService = new AuthenticationService();
        if(authenticationService.validToken(encryptedToken)) {
            String message = cartService.placeOrder(addressId, authenticationService.getTokenDetails(encryptedToken));
            if(message.contains("ORDER PLACED"))
                return ResponseEntity.status(HttpStatus.OK).body(message);
            if(message.equals("ADDRESS ID MISMATCH"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("AddressId Not present..please add.. (CODE 409)");
            if(message.equals("EMPTY CART"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("EMPTY CART (CODE 409)");
           return  ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN (CODE 409)");
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID TOKEN (CODE 401)");
    }

    @GetMapping("/get-item-status/{itemId}")
    public ResponseEntity<String>  itemStatus(@PathVariable Long itemId){
       String message =  cartService.itemsStatus(itemId);
       if(message.contains("ITEM STATUS"))
           return ResponseEntity.status(HttpStatus.FOUND).body(message);
       if(message.equals("NULL"))
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID ORDER ID");
       return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN ERROR");
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handle(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST) .body("Returning HTTP 400 Bad Request");
    }

}
