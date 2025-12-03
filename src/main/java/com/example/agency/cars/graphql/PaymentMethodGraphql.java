package com.example.agency.cars.graphql;

import com.example.agency.cars.dto.PaymentMethodRequest;
import com.example.agency.cars.dto.PaymentMethodResponse;
import com.example.agency.cars.service.PaymentMethodService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PaymentMethodGraphql {

    private final PaymentMethodService service;

    public PaymentMethodGraphql(PaymentMethodService service) {
        this.service = service;
    }

    @QueryMapping
    public List<PaymentMethodResponse> listPaymentMethods() {
        return service.listPaymentMethods();
    }

    @MutationMapping
    public PaymentMethodResponse registerPaymentMethod(@Argument PaymentMethodRequest data) {
        return service.registerPaymentMethod(data);
    }
}
