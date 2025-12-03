package com.example.agency.cars.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())           // Desactiva CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()       // TODO público
                )
                .httpBasic(httpBasic -> httpBasic.disable())   // Desactiva Basic Auth
                .formLogin(form -> form.disable())             // Desactiva el login form
                .logout(logout -> logout.disable());           // Desactiva logout

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
