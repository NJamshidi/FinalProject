package com.project.homeservicesystem.entities.dto;

import com.project.homeservicesystem.entities.services.MainService;
import lombok.Data;

import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Data
public class ServiceCategoryDto {
    private Long id;
    private String name;
    private double basePrice;
    private String description;
    private MainService mainService;
}
