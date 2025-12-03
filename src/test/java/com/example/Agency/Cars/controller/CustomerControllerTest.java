package com.example.Agency.Cars.controller;

import com.example.agency.cars.controller.CustomerController;
import com.example.agency.cars.dto.CustomerRequest;
import com.example.agency.cars.dto.CustomerResponse;
import com.example.agency.cars.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CustomerService service;

    private static final String BASE = "/api/v1/customers";

    @BeforeEach
    void setup() {
        reset(service);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        CustomerService customerService() {
            return Mockito.mock(CustomerService.class);
        }
    }

    // === Helpers ===
private CustomerResponse resp(int id, String name, String lastname) {
    return CustomerResponse.builder()
            .idCustomer(id)
            .name(name)
            .lastName(lastname)
            .build();
}


    private CustomerRequest req(String name, String lastname) {
        CustomerRequest r = new CustomerRequest();
        r.setName(name);
        r.setLastName(lastname);
        return r;
    }

    // === Tests ===

    @Test
    @DisplayName("POST /api/v1/customers → 201 Created")
    void registerCustomer_ok() throws Exception {
        CustomerRequest rq = req("Alexis", "Rodriguez");
        CustomerResponse created = resp(1, "Alexis", "Rodriguez");

        when(service.registerCustomer(any(CustomerRequest.class))).thenReturn(created);

        mvc.perform(post(BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_customer").value(1))
                .andExpect(jsonPath("$.name").value("Alexis"));
    }

    @Test
    @DisplayName("GET /api/v1/customers → 200 OK con lista")
    void getAllCustomers_ok() throws Exception {
        when(service.getAllCustomers()).thenReturn(List.of(resp(1, "Alexis", "Rodriguez")));

        mvc.perform(get(BASE).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alexis"));
    }

    @Test
    @DisplayName("GET /api/v1/customers → 200 OK con lista vacía")
    void getAllCustomers_empty() throws Exception {
        when(service.getAllCustomers()).thenReturn(Collections.emptyList());

        mvc.perform(get(BASE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("GET /api/v1/customers/search/{name} → 200 OK")
    void findCustomersByName_ok() throws Exception {
        when(service.findCustomersByName("alexis"))
                .thenReturn(List.of(resp(1, "Alexis", "Rodriguez")));

        mvc.perform(get(BASE + "/search/{name}", "alexis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alexis"));
    }

    @Test
    @DisplayName("PUT /api/v1/customers/{id} → 200 OK")
    void updateCustomer_ok() throws Exception {
        CustomerRequest rq = req("Alexis", "Rodriguez");
        CustomerResponse updated = resp(1, "Alexis", "Rodriguez");

        when(service.updateCustomer(eq(1), any(CustomerRequest.class))).thenReturn(updated);

        mvc.perform(put(BASE + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(rq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id_customer").value(1));
    }

    @Test
    @DisplayName("DELETE /api/v1/customers/{id} → 204 No Content")
    void deleteCustomer_ok() throws Exception {
        doNothing().when(service).deleteCustomer(1);

        mvc.perform(delete(BASE + "/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
