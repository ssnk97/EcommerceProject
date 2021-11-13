package com.cjss.accountservice.model;

import javax.validation.constraints.Email;

public class LoginModel {
    @Email(message = "enter a valid email")
    private String userEmail;
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
