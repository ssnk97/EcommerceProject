package com.cjss.accountservice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class CustomerModel {

    @NotNull(message = "firstName must not be null")
    @NotEmpty(message = "firstName must not be empty")
    @Pattern(regexp="^[a-zA-z]+([\\s][a-zA-Z]+)*$",message="ONLY ALPHABETS ALLOWED FOR  FIRST NAME :: or :: ONLY ONE SPACE BETWEEN 2 NAMES")
    private String firstName;
    @NotNull(message = "lastName must not be null")
    @NotEmpty(message = "lastName must not be empty")
    @Pattern(regexp="^[a-zA-z]+([\\s][a-zA-Z]+)*$",message="ONLY ALPHABETS ALLOWED FOR LAST NAME :: or :: ONLY ONE SPACE BETWEEN 2 NAMES")
    private String lastName;
    @Email(message = "enter a valid email")
    private String email;
    @NotNull(message = "mobileNumber must not be null")
    @NotEmpty(message = "mobileNumber must not be empty")
    @Pattern(regexp="^\\d{10}$",message="Enter a valid mobile number")
    private String  mobileNumber;

    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;
    private List<AddressModel> addressModelList;


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

    public List<AddressModel> getAddressModelList() {
        return addressModelList;
    }

    public void setAddressModelList(List<AddressModel> addressModelList) {
        this.addressModelList = addressModelList;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", password='" + password + '\'' +
                ", addressModelList=" + addressModelList +
                '}';
    }
}
