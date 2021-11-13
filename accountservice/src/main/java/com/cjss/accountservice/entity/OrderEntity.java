package com.cjss.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="email", referencedColumnName = "email" )
    @JsonIgnoreProperties("orderEntityList")
    private CustomerEntity customerEntity;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "shipping_address", referencedColumnName ="id")
    private ShippingAddressEntity shippingAddressEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEntity", fetch = FetchType.LAZY)
    private List<ItemsOrderedEntity> itemsOrderedEntityList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ShippingAddressEntity getShippingAddressEntity() {
        return shippingAddressEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public void setShippingAddressEntity(ShippingAddressEntity shippingAddressEntity) {
        this.shippingAddressEntity = shippingAddressEntity;
    }

    public List<ItemsOrderedEntity> getItemsOrderedEntityList() {
        return itemsOrderedEntityList;
    }

    public void setItemsOrderedEntityList(List<ItemsOrderedEntity> itemsOrderedEntityList) {
        this.itemsOrderedEntityList = itemsOrderedEntityList;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", customerEntity=" + customerEntity +
                ", shippingAddressEntity=" + shippingAddressEntity +
                '}';
    }

    //    public BillingEntity getBillingEntity() {
//        return billingEntity;
//    }
//
//    public void setBillingEntity(BillingEntity billingEntity) {
//        this.billingEntity = billingEntity;
//    }
}
