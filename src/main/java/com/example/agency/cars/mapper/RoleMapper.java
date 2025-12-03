package com.example.agency.cars.mapper;

import com.example.agency.cars.dto.RoleResponse;
import com.example.agency.cars.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .idRole(role.getIdRole())
                .description(role.getDescription())
                .build();
    }
}
