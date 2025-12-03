package com.example.agency.cars.service;

import com.example.agency.cars.dto.SaleRequest;
import com.example.agency.cars.dto.SaleResponse;
import com.example.agency.cars.mapper.SaleMapper;
import com.example.agency.cars.model.Customer;
import com.example.agency.cars.model.PaymentMethod;
import com.example.agency.cars.model.Sale;
import com.example.agency.cars.repository.CustomerRepository;
import com.example.agency.cars.repository.PaymentMethodRepository;
import com.example.agency.cars.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public SaleResponse registerSale(SaleRequest data) {
        Customer customer = customerRepository.findById(data.getIdCustomer())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + data.getIdCustomer()));

        PaymentMethod method = paymentMethodRepository.findById(data.getIdMethod())
                .orElseThrow(() -> new EntityNotFoundException("Payment method not found with id: " + data.getIdMethod()));

        Sale sale = SaleMapper.toEntity(data, customer, method);
        Sale saved = saleRepository.save(sale);
        return SaleMapper.toResponse(saved);
    }

    @Override
    public SaleResponse getSaleById(Integer idSale) {
        Sale sale = saleRepository.findById(idSale)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + idSale));

        return SaleMapper.toResponse(sale);
    }
}
