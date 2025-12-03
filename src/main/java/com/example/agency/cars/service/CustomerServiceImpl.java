package com.example.agency.cars.service;

import com.example.agency.cars.dto.CustomerRequest;
import com.example.agency.cars.dto.CustomerResponse;
import com.example.agency.cars.mapper.CustomerMapper;
import com.example.agency.cars.model.Customer;
import com.example.agency.cars.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse registerCustomer(CustomerRequest request) {
        Customer customer = CustomerMapper.toEntity(request);
        Customer saved = customerRepository.save(customer);
        return CustomerMapper.toResponse(saved);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponse> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        return customers.stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(Integer customerId, CustomerRequest request) {
        Customer existing = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        CustomerMapper.copyToEntity(request, existing);
        Customer updated = customerRepository.save(existing);

        return CustomerMapper.toResponse(updated);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
        customerRepository.deleteById(customerId);
    }
}
