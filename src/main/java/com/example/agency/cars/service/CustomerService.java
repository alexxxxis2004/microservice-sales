package com.example.agency.cars.service;

import com.example.agency.cars.dto.CustomerRequest;
import com.example.agency.cars.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    // Register a new customer
    CustomerResponse registerCustomer(CustomerRequest request);

    // List all customers
    List<CustomerResponse> getAllCustomers();

    // Search customers by name
    List<CustomerResponse> findCustomersByName(String name);

    // Update an existing customer
    CustomerResponse updateCustomer(Integer customerId, CustomerRequest request);

    // Delete a customer by ID
    void deleteCustomer(Integer customerId);
}
