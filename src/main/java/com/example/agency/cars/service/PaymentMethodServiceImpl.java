package com.example.agency.cars.service;

import com.example.agency.cars.dto.PaymentMethodRequest;
import com.example.agency.cars.dto.PaymentMethodResponse;
import com.example.agency.cars.mapper.PaymentMethodMapper;
import com.example.agency.cars.model.PaymentMethod;
import com.example.agency.cars.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository repository;

    @Override
    public PaymentMethodResponse registerPaymentMethod(PaymentMethodRequest data) {
        PaymentMethod entity = PaymentMethodMapper.toEntity(data);
        PaymentMethod saved = repository.save(entity);
        return PaymentMethodMapper.toResponse(saved);
    }

    @Override
    public List<PaymentMethodResponse> listPaymentMethods() {
        return repository.findAll()
                .stream()
                .map(PaymentMethodMapper::toResponse)
                .collect(Collectors.toList());
    }
}
