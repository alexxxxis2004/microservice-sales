package com.example.agency.cars.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RoleResponse {
    private Integer idRole;
    private String rolename;
}
