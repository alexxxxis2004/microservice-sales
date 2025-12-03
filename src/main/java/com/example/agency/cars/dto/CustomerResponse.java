package com.example.agency.cars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerResponse {
    @JsonProperty("id_customer")
    Integer idCustomer;
    String name;
    @JsonProperty("Last name")
    String lastName;
    String email;
    String phone;
    String rfc;
    String address;
}
