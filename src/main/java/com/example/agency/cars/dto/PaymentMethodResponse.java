package com.example.agency.cars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentMethodResponse {
    private Integer idMethod;
    @JsonProperty("Method name")
    private String methodName;
    private String description;
}
