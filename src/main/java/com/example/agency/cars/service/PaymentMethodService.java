package com.example.agency.cars.service;

import com.example.agency.cars.dto.PaymentMethodRequest;
import com.example.agency.cars.dto.PaymentMethodResponse;
import java.util.List;

public interface PaymentMethodService {
    PaymentMethodResponse registerPaymentMethod(PaymentMethodRequest data);

    List<PaymentMethodResponse> listPaymentMethods();
}
