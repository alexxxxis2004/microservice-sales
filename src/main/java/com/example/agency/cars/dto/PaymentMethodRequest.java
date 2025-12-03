package com.example.agency.cars.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentMethodRequest {

    @NotBlank(message = "The method name is mandatory")
    @Size(max = 100, message = "The method name cannot exceed 100 characters")
    private String methodName;

    @Size(max = 255, message = "The description cannot exceed 255 characters")
    private String description;
}
