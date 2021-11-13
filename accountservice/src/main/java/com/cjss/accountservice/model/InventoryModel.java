package com.cjss.accountservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class InventoryModel {

    @NotNull(message = "quantityAvailable must not be null")
    @NotEmpty(message = "quantityAvailable must not be empty")
    @Pattern(regexp="^[0-9]",message="Enter numeric data for quantityAvailable")
    private String quantityAvailable;
    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
