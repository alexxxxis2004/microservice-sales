package com.example.agency.cars.repository;

import com.example.agency.cars.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRolename(String rolename);
}
