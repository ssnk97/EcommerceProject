package com.cjss.accountservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductSizeModel {
    @NotNull(message = "SkuCode must not be null")
    @NotEmpty(message = "SkuCode must not be empty")
    private String skuCode;
    @NotNull(message = "productName must not be null")
    @NotEmpty(message = "productName must not be empty")
    private String productCode;
    @NotNull(message = "size must not be null")
    @NotEmpty(message = "size must not be empty")
    private String size;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
