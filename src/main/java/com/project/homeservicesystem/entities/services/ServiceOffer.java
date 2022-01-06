package com.project.homeservicesystem.entities.services;

import com.project.homeservicesystem.entities.users.Provider;
import com.project.homeservicesystem.enumaration.ServiceOfferStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ServiceOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date submitDate = new Date();
    private Integer startHour;
    private double price;
    private int durationInHours;
    @ManyToOne
    private ServiceRequest serviceRequest;
    @ManyToOne
    private Provider provider;
    @Enumerated(EnumType.STRING)
    private ServiceOfferStatus serviceOfferStatus;
}
