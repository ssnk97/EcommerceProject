package com.cjss.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Product_Size")
public class ProductSizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String skuCode;
    @Column
    private String size;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="productCode", referencedColumnName = "productCode" )
    @JsonIgnoreProperties("productSizeList")
    private ProductEntity productEntity;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "price_sku")
    private ProductPriceEntity productPriceEntity;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "inventory_sku")
    private InventoryEntity inventoryEntity;




    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public ProductPriceEntity getProductPriceEntity() {
        return productPriceEntity;
    }

    public void setProductPriceEntity(ProductPriceEntity productPriceEntity) {
        this.productPriceEntity = productPriceEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public InventoryEntity getInventoryEntity() {
        return inventoryEntity;
    }

    public void setInventoryEntity(InventoryEntity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
    }
}
