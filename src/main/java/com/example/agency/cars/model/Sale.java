package com.example.agency.cars.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Integer idSale;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "sale_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date saleDate;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;
    
    @JoinColumn(name = "id_vehicle", nullable = false)
    private Integer id_vehicle;
}
