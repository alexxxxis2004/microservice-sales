package com.example.agency.cars.graphql;

import com.example.agency.cars.dto.SaleRequest;
import com.example.agency.cars.dto.SaleResponse;
import com.example.agency.cars.service.SaleService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SaleGraphql {

    private final SaleService service;

    public SaleGraphql(SaleService service) {
        this.service = service;
    }

    @QueryMapping
    public SaleResponse getSaleById(@Argument Integer idSale) {
        return service.getSaleById(idSale);
    }

    @MutationMapping
    public SaleResponse registerSale(@Argument SaleRequest data) {
        return service.registerSale(data);
    }
}
