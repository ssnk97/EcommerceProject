package com.cjss.accountservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressModel {

   private Long addressId;
   @NotNull(message = "line1 must not be null")
   @NotEmpty(message = "line1 must not be empty")
   private String line1;
   private String line2;
   @NotEmpty(message = "postalCode must not be empty")
   @NotNull(message = "postalCode must not be null")
   @Pattern(regexp="^\\d{6}$",message="Enter a valid PostalCode")
   private String postalCode;
   @NotNull(message = "city must not be null")
   @NotEmpty(message = "city must not be empty")
   @Pattern(regexp="^[a-zA-z]+([\\s][a-zA-Z]+)*$",message="ONLY ALPHABETS ALLOWED FOR  City :: or :: ONLY ONE SPACE BETWEEN 2 Words")
   private String city;
   @NotNull(message = "state must not be null")
   @NotEmpty(message = "state must not be empty")
   @Pattern(regexp="^[a-zA-z]+([\\s][a-zA-Z]+)*$",message="ONLY ALPHABETS ALLOWED FOR  State :: or :: ONLY ONE SPACE BETWEEN 2 Words")
   private String state;
   @NotNull(message = "shippingAddress must not be null")
   private boolean shippingAddress;
   @NotNull (message = "billingAddress must not be null")
   private boolean billingAddress;

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

    @Override
    public String toString() {
        return "AddressModel{" +
                "addressId=" + addressId +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                '}';
    }
}
