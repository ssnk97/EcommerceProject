package com.cjss.accountservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ProductPriceModel {
    @NotNull(message = "SkuCode must not be null")
    @NotEmpty(message = "SkuCode must not be empty")
    private String skuCode;
    @NotNull(message = "price must not be null")
    @NotEmpty(message = "price must not be empty")
    //@Pattern(regexp="^[0-9]",message="Enter numeric data for price")
    private String price;
    @NotNull(message = "Currency must not be null")
    @NotEmpty(message = "Currency must not be empty")
    private String currency;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
