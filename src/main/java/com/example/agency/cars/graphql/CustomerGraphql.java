package com.example.agency.cars.graphql;

import com.example.agency.cars.dto.CustomerRequest;
import com.example.agency.cars.dto.CustomerResponse;
import com.example.agency.cars.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerGraphql {

    private final CustomerService service;

    public CustomerGraphql(CustomerService service) {
        this.service = service;
    }

    @QueryMapping
    public List<CustomerResponse> getAllCustomers() {
        return service.getAllCustomers();
    }

    @QueryMapping
    public List<CustomerResponse> findCustomersByName(@Argument String name) {
        return service.findCustomersByName(name);
    }

    @MutationMapping
    public CustomerResponse registerCustomer(@Argument CustomerRequest request) {
        return service.registerCustomer(request);
    }

    @MutationMapping
    public CustomerResponse updateCustomer(@Argument Integer idCustomer, @Argument CustomerRequest request) {
        return service.updateCustomer(idCustomer, request);
    }

    @MutationMapping
    public Boolean deleteCustomer(@Argument Integer idCustomer) {
        service.deleteCustomer(idCustomer);
        return true;
    }
}
