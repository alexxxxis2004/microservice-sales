package com.example.agency.cars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SatisfactionSurveyResponse {
    private Integer idSurvey;
    private Integer idCustomer;
    @JsonProperty("Customer name")
    private String customerName;
    private Integer idSale;
    private Integer rating;
    private String comments;
    private String sentimentLabel;
    private Double sentimentScore;
}
