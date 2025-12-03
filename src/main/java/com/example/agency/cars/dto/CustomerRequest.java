package com.example.agency.cars.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank(message = "The name is mandatory")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "The last name is mandatory")
    @Size(max = 50)
    @JsonProperty("last_name")
    private String lastName;

    @Email(message = "The email format is invalid")
    @Size(max = 100, message = "The email must not exceed 100 characters")
    private String email;

    @Size(max = 13, message = "The phone number must not exceed 13 digits")
    @Pattern(regexp = "^[0-9]{10,13}$", message = "The phone number must contain only digits and be between 10 and 13 characters long")
    private String phone;

    @NotBlank(message = "The RFC is mandatory")
    @Size(max = 13, message = "The RFC must not exceed 13 characters")
    @Pattern(regexp = "^[A-Z&Ñ]{3,4}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])[A-Z0-9]{3}$", message = "Invalid RFC format")
    private String rfc;

    @NotBlank(message = "The address is mandatory")
    @Size(max = 100)
    private String address;
}
