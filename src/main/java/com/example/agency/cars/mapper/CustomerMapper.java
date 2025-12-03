package com.example.agency.cars.mapper;

import com.example.agency.cars.dto.CustomerRequest;
import com.example.agency.cars.dto.CustomerResponse;
import com.example.agency.cars.model.Customer;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerResponse toResponse(Customer customer) {
        if (customer == null)
            return null;

        return CustomerResponse.builder()
                .idCustomer(customer.getIdCustomer())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .rfc(customer.getRfc())
                .address(customer.getAddress())
                .build();
    }

    public static Customer toEntity(CustomerRequest dto) {
        if (dto == null)
            return null;

        return Customer.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .rfc(dto.getRfc())
                .address(dto.getAddress())
                .build();
    }

    public static void copyToEntity(CustomerRequest dto, Customer entity) {
        if (dto == null || entity == null)
            return;

        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setRfc(dto.getRfc());
        entity.setAddress(dto.getAddress());
    }
}
