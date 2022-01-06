package com.project.homeservicesystem.entities.services;

import com.project.homeservicesystem.entities.users.Customer;
import com.project.homeservicesystem.enumaration.ServiceRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private String description;
    private Date submitDate;
    private Date startDate;
    private String address;
    @ManyToOne
    private MainService mainService;
    private ServiceRequestStatus status = ServiceRequestStatus.UNDER_OFFERING;
    @ManyToOne
    private Customer customer;
}
