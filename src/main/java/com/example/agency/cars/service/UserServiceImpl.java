package com.example.agency.cars.service;

import com.example.agency.cars.dto.UserRequest;
import com.example.agency.cars.dto.UserResponse;
import com.example.agency.cars.mapper.UserMapper;
import com.example.agency.cars.model.Role;
import com.example.agency.cars.model.User;
import com.example.agency.cars.repository.RoleRepository;
import com.example.agency.cars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserResponse create(UserRequest request) {
        String role = request.getRole();
        Role userRole = roleRepository.findByDescription(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = UserMapper.toEntity(request);
        user.setAuthorities(authorities);
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }
}
