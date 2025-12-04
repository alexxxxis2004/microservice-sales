package com.example.agency.cars.service;

import com.example.agency.cars.dto.SatisfactionSurveyRequest;
import com.example.agency.cars.dto.SatisfactionSurveyResponse;
import java.util.List;

public interface SatisfactionSurveyService {
    SatisfactionSurveyResponse registerSurvey(SatisfactionSurveyRequest data);

    List<SatisfactionSurveyResponse> getSurveysByCustomer(Integer idCustomer);

    Double getOverallSatisfactionAverage();
}
