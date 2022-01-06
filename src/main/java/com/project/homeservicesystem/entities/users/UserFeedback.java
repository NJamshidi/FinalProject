package com.project.homeservicesystem.entities.users;

import com.project.homeservicesystem.entities.services.ServiceRequest;
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
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private ServiceRequest serviceRequest;
    private int rate;
}
