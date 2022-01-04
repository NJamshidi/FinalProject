package com.project.homeservicesystem.entities.users;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @MappedSuperclass
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        @Column(nullable = false, unique = true)
        private String email;
        private String password;
        private Date registerDate = new Date();
        private double credit;
    }

