package com.cjss.accountservice.controller;

import com.cjss.accountservice.entity.InventoryEntity;
import com.cjss.accountservice.entity.ProductSizeEntity;
import com.cjss.accountservice.model.InventoryModel;
import com.cjss.accountservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PatchMapping("/add-inventory")
    public ProductSizeEntity addInventory(@Valid @RequestBody InventoryModel inventoryModel, @RequestParam String skuCode){
        return inventoryService.addInventory(inventoryModel, skuCode);
    }

}

