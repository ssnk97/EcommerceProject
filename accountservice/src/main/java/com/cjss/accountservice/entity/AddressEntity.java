package com.cjss.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column
    private String line1;
    @Column
    private String line2;
    @Column
    private String postalCode;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private boolean shippingAddress;
    @Column
    private boolean billingAddress;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="email", referencedColumnName = "email" )
    @JsonIgnoreProperties("addressEntityList")
    CustomerEntity customerEntity;

    public AddressEntity() {}


    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(boolean shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public boolean isBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(boolean billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "addressId=" + addressId +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", customerEntity=" + customerEntity +
                '}';
    }
}
