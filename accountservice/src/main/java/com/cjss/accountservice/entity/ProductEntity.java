package com.cjss.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name= "PRODUCTS")
public class ProductEntity {

    @Id
    private String productCode;
    @Column
    private String productName;
    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productEntity", fetch = FetchType.EAGER)
    private List<ProductSizeEntity> productSizeList;



    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductSizeEntity> getProductSizeList() {
        return productSizeList;
    }

    public void setProductSizeList(List<ProductSizeEntity> productSizeList) {
        this.productSizeList = productSizeList;
    }
}
