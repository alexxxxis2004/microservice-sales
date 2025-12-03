package com.example.agency.cars.service;

import com.example.agency.cars.dto.RoleRequest;
import com.example.agency.cars.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    List<RoleResponse> listRoles();
}
