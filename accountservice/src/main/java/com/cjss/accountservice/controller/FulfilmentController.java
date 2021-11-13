package com.cjss.accountservice.controller;

import com.cjss.accountservice.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FulfilmentController {
    @Autowired
    FulfilmentService fulfilmentService;

    @PatchMapping("/update-item-status")
    public ResponseEntity<String> updateItemStatus(@RequestParam Long itemId, @RequestParam String status){
        System.out.println(itemId+"  status: "+ status);
        String message = fulfilmentService.updateItemStatus(itemId, status);
        if(message.contains("STATUS UPDATED"))
            return ResponseEntity.status(HttpStatus.OK).body(message);
        if(message.equals("INVALID ID"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("UNKNOWN");
    }
}
