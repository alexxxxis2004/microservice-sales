package com.example.agency.cars.service;

import com.example.agency.cars.dto.SatisfactionSurveyRequest;
import com.example.agency.cars.dto.SatisfactionSurveyResponse;
import com.example.agency.cars.mapper.SatisfactionSurveyMapper;
import com.example.agency.cars.model.Customer;
import com.example.agency.cars.model.Sale;
import com.example.agency.cars.model.SatisfactionSurvey;
import com.example.agency.cars.repository.CustomerRepository;
import com.example.agency.cars.repository.SaleRepository;
import com.example.agency.cars.repository.SatisfactionSurveyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SatisfactionSurveyServiceImpl implements SatisfactionSurveyService {

    private final SatisfactionSurveyRepository repository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final AzureSentimentService sentimentService;

    @Override
    public SatisfactionSurveyResponse registerSurvey(SatisfactionSurveyRequest data) {

        Customer customer = customerRepository.findById(data.getIdCustomer())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + data.getIdCustomer()));

        Sale sale = saleRepository.findById(data.getIdSale())
                .orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + data.getIdSale()));

        SatisfactionSurvey entity = SatisfactionSurveyMapper.toEntity(data, customer, sale);
        
        JsonNode aiResult = sentimentService.analyze(entity.getComments());

        String label = aiResult.at("/results/documents/0/sentiment").asText();
        double score = aiResult.at("/results/documents/0/confidenceScores/positive").asDouble();

        entity.setSentimentLabel(label);
        entity.setSentimentScore(score);

        SatisfactionSurvey saved = repository.save(entity);
        return SatisfactionSurveyMapper.toResponse(saved);
    }

    @Override
    public List<SatisfactionSurveyResponse> getSurveysByCustomer(Integer idCustomer) {
        return repository.findByCustomerIdCustomer(idCustomer)
                .stream()
                .map(SatisfactionSurveyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Double getOverallSatisfactionAverage() {
        Double avg = repository.calculateAverageRating();
        return avg != null ? avg : 0.0;
    }
}
