package com.example.agency.cars.service;

import com.example.agency.cars.dto.RoleRequest;
import com.example.agency.cars.dto.RoleResponse;
import com.example.agency.cars.mapper.RoleMapper;
import com.example.agency.cars.model.Role;
import com.example.agency.cars.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = new Role();
        role.setDescription(request.getDescription());
        Role saved = roleRepository.save(role);
        return roleMapper.toResponse(saved);
    }

    @Override
    public List<RoleResponse> listRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .collect(Collectors.toList());
    }
}
