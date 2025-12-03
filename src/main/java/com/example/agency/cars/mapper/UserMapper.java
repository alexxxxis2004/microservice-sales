package com.example.agency.cars.mapper;

import java.util.Iterator;
import java.util.Set;

import com.example.agency.cars.dto.UserRequest;
import com.example.agency.cars.dto.UserResponse;
import com.example.agency.cars.model.Role;
import com.example.agency.cars.model.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        if (user == null) return null;

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();

        if (roles != null && !roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }

        return UserResponse.builder()
                .username(user.getUsername())
                .role(firstElement.getDescription())
                .build();
    }

    public static User toEntity(UserRequest dto) {
        if (dto == null) return null;

        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static void copyToEntity(UserRequest dto, User entity) {
        if (dto == null || entity == null) return;

        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
    }
}
