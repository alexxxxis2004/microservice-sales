package com.example.agency.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.agency.cars.dto.CustomerRequest;
import com.example.agency.cars.dto.CustomerResponse;
import com.example.agency.cars.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@Tag(name = "Customer", description = "Provides REST operations for managing customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Operation(summary = "Register a new customer")
    @ApiResponse(responseCode = "201", description = "Customer registered successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponse.class)))
    @PostMapping
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request) {
        CustomerResponse savedCustomer = service.registerCustomer(request);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all customers")
    @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))))
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = service.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Find customers by exact name")
    @ApiResponse(responseCode = "200", description = "Customers found",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CustomerResponse.class))))
    @GetMapping("/search/{name}")
    public ResponseEntity<List<CustomerResponse>> findCustomersByName(@PathVariable String name) {
        List<CustomerResponse> customers = service.findCustomersByName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing customer")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponse.class)))
    @PutMapping("/{id_customer}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Integer id_customer,
            @RequestBody CustomerRequest request) {

        CustomerResponse updatedCustomer = service.updateCustomer(id_customer, request);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @Operation(summary = "Delete a customer by ID")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @DeleteMapping("/{id_customer}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id_customer) {
        service.deleteCustomer(id_customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
