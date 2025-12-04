package com.example.agency.cars.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SatisfactionSurveyRequest {

    @NotNull(message = "Customer ID is required")
    private Integer idCustomer;

    @NotNull(message = "Sale ID is required")
    private Integer idSale;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private Integer rating;

    @Size(max = 500, message = "Comments cannot exceed 500 characters")
    private String comments;
}
