package com.example.agency.cars.mapper;

import com.example.agency.cars.dto.SatisfactionSurveyRequest;
import com.example.agency.cars.dto.SatisfactionSurveyResponse;
import com.example.agency.cars.model.Customer;
import com.example.agency.cars.model.Sale;
import com.example.agency.cars.model.SatisfactionSurvey;

public class SatisfactionSurveyMapper {

    public static SatisfactionSurveyResponse toResponse(SatisfactionSurvey entity) {
        if (entity == null) return null;

        return SatisfactionSurveyResponse.builder()
                .idSurvey(entity.getIdSurvey())
                .idCustomer(entity.getCustomer().getIdCustomer())
                .customerName(entity.getCustomer().getName())
                .idSale(entity.getSale().getIdSale())
                .rating(entity.getRating())
                .comments(entity.getComments())
                .sentimentLabel(entity.getSentimentLabel())
                .sentimentScore(entity.getSentimentScore())
                .build();
    }

    public static SatisfactionSurvey toEntity(SatisfactionSurveyRequest dto, Customer customer, Sale sale) {
        if (dto == null) return null;

        return SatisfactionSurvey.builder()
                .customer(customer)
                .sale(sale)
                .rating(dto.getRating())
                .comments(dto.getComments())
                .build();
    }
}
