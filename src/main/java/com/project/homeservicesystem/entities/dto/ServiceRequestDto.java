package com.project.homeservicesystem.entities.dto;

import com.project.homeservicesystem.entities.services.ServiceCategory;
import com.project.homeservicesystem.entities.users.Customer;
import com.project.homeservicesystem.enumaration.ServiceRequestStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;
@Data
public class ServiceRequestDto {
    private Long id;
    private double price;
    private String description;
    private Date submitDate;
    private Date startDate;
    private String address;
    private ServiceCategory serviceCategory;
    private ServiceRequestStatus status ;
    private Customer customer;
}
