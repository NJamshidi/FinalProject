package com.project.homeservicesystem.entities.dto;

import com.project.homeservicesystem.entities.services.ServiceRequest;
import com.project.homeservicesystem.entities.users.Provider;
import com.project.homeservicesystem.enumaration.ServiceOfferStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;
@Data
@Builder
public class ServiceOfferDto {
    private Long id;
    private Date submitDate;
    private Integer startHour;
    private double price;
    private int durationInHours;
    private ServiceRequest serviceRequest;
    private Provider provider;
    private ServiceOfferStatus serviceOfferStatus;
}
