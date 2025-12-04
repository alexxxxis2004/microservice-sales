package com.example.agency.cars.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "satisfactionsurveys")
public class SatisfactionSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_survey")
    private Integer idSurvey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sale", nullable = false, unique = true)
    private Sale sale;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comments", length = 500)
    private String comments;

    @Column(name = "sentimentlabel")
    private String sentimentLabel;

    @Column(name = "sentimentscore")
    private Double sentimentScore;
}
