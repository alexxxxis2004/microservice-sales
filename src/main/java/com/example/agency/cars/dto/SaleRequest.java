package com.example.agency.cars.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Date;

@Data
public class SaleRequest {

    @NotNull(message = "The customer ID is required")
    private Integer idCustomer;

    @NotNull(message = "The payment method ID is required")
    private Integer idMethod;

    @NotNull(message = "The sale date is required")
    @PastOrPresent(message = "The sale date cannot be in the future")
    private Date saleDate;

    @NotNull(message = "The total amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "The total amount must be greater than zero")
    private Double totalAmount;
}
