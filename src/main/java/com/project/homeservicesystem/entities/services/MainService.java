package com.project.homeservicesystem.entities.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MainService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double basePrice;
    private String description;
    @ManyToOne
    private ServiceCategory category;
}
