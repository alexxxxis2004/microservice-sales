package com.example.agency.cars.model;

import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role", nullable = false)
    private Integer idRole;

    @Column(name = "description", nullable = false, unique = true, length = 100)
    private String description;

    @Override
    public String getAuthority() {
        return description;
    }
}
