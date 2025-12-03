package com.example.agency.cars.service;

import com.example.agency.cars.dto.SaleRequest;
import com.example.agency.cars.dto.SaleResponse;

public interface SaleService {
    SaleResponse registerSale(SaleRequest data);

    SaleResponse getSaleById(Integer idSale);
}
