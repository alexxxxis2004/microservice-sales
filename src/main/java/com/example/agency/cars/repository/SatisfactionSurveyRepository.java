package com.example.agency.cars.repository;

import com.example.agency.cars.model.SatisfactionSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SatisfactionSurveyRepository extends JpaRepository<SatisfactionSurvey, Integer> {

    List<SatisfactionSurvey> findByCustomerIdCustomer(Integer idCustomer);

    @Query("SELECT AVG(s.rating) FROM SatisfactionSurvey s")
    Double calculateAverageRating();
}
