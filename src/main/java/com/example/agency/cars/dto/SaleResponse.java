package com.example.agency.cars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class SaleResponse {

    private Integer idSale;
    private Integer idCustomer;
    @JsonProperty ("Customer name")
    private String customerName;
    private Integer idMethod;
    @JsonProperty ("Method name")
    private String methodName;
    @JsonProperty ("Sale date")
    private Date saleDate;
    private Double totalAmount;
}
