package com.example.agency.cars.mapper;

import com.example.agency.cars.dto.PaymentMethodRequest;
import com.example.agency.cars.dto.PaymentMethodResponse;
import com.example.agency.cars.model.PaymentMethod;

public class PaymentMethodMapper {

    public static PaymentMethodResponse toResponse(PaymentMethod entity) {
        if (entity == null) return null;

        return PaymentMethodResponse.builder()
                .idMethod(entity.getIdMethod())
                .methodName(entity.getMethodName())
                .description(entity.getDescription())
                .build();
    }

    public static PaymentMethod toEntity(PaymentMethodRequest dto) {
        if (dto == null) return null;

        return PaymentMethod.builder()
                .methodName(dto.getMethodName())
                .description(dto.getDescription())
                .build();
    }
}
