package com.example.agency.cars.mapper;

import com.example.agency.cars.dto.SaleRequest;
import com.example.agency.cars.dto.SaleResponse;
import com.example.agency.cars.model.Customer;
import com.example.agency.cars.model.PaymentMethod;
import com.example.agency.cars.model.Sale;

public class SaleMapper {

    public static SaleResponse toResponse(Sale entity) {
        if (entity == null) return null;

        return SaleResponse.builder()
                .idSale(entity.getIdSale())
                .idCustomer(entity.getCustomer() != null ? entity.getCustomer().getIdCustomer() : null)
                .customerName(entity.getCustomer() != null ? entity.getCustomer().getName() : null)
                .idMethod(entity.getPaymentMethod() != null ? entity.getPaymentMethod().getIdMethod() : null)
                .methodName(entity.getPaymentMethod() != null ? entity.getPaymentMethod().getMethodName() : null)
                .saleDate(entity.getSaleDate())
                .totalAmount(entity.getTotalAmount())
                .build();
    }

    public static Sale toEntity(SaleRequest dto, Customer customer, PaymentMethod method) {
        if (dto == null) return null;

        return Sale.builder()
                .customer(customer)
                .paymentMethod(method)
                .saleDate(dto.getSaleDate())
                .totalAmount(dto.getTotalAmount())
                .build();
    }
}
