package com.cjss.accountservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CartModel {
    @NotNull(message = "productCode must not be null")
    @NotEmpty(message = "productCode must not be empty")
    private String productCode;
    @NotNull(message = "SkuCode must not be null")
    @NotEmpty(message = "SkuCode must not be empty")
    private String skuCode;
    @NotNull(message = "quantity must not be null")
    @NotEmpty(message = "quantity must not be empty")
    @Pattern(regexp="^[0-9]",message="Enter numeric data for quantity")
    private String quantity;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
