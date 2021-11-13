package com.cjss.accountservice.entity;


import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
public class CustomerEntity {

    @Column
    private String firstName;
    @Column
    private String lastName;

    @Id
    private String email;
    @Column
    private String mobileNumber;
    @Column
    private String password;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private List<AddressEntity> addressEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartEntity> cartEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList;


    @Override
    public String toString() {
        return "CustomerEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                ", addressEntityList=" + addressEntityList +
                ", cartEntityList=" + cartEntityList +
                ", orderEntityList=" + orderEntityList +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressEntity> getAddressEntityList() {
        return addressEntityList;
    }

    public void setAddressEntityList(List<AddressEntity> addressEntityList) {
        this.addressEntityList = addressEntityList;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public List<CartEntity> getCartEntityList() {
        return cartEntityList;
    }

    public void setCartEntityList(List<CartEntity> cartEntityList) {
        this.cartEntityList = cartEntityList;
    }
}
