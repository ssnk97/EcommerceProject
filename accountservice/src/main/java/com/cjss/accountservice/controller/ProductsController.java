package com.cjss.accountservice.controller;

import com.cjss.accountservice.entity.ProductEntity;
import com.cjss.accountservice.entity.ProductSizeEntity;
import com.cjss.accountservice.model.ProductModel;
import com.cjss.accountservice.model.ProductPriceModel;
import com.cjss.accountservice.model.ProductSizeModel;
import com.cjss.accountservice.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @PostMapping("/add-product")
    public ProductEntity addProduct(@Valid @RequestBody ProductModel productModel){
        return productsService.addProduct(productModel);
    }

    @PatchMapping("/add-sku")
    public ProductEntity addSkusSize(@Valid @RequestBody ProductSizeModel productSizeModel){
        return productsService.addSkusSize(productSizeModel);
    }

    @PatchMapping("/add-price")
    public ProductSizeEntity addPrice(@Valid @RequestBody ProductPriceModel productPriceModel){
        return productsService.addPrice(productPriceModel);
    }
}
