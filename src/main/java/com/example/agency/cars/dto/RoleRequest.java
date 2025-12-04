package com.example.agency.cars.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequest {
    @NotBlank(message = "Role Name is required")
    private String rolename;
}
