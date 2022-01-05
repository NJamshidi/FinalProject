package com.project.homeservicesystem.entities.users;

import com.project.homeservicesystem.enumaration.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    @Column(nullable = false)
    private String userName;
    private String password;
    private Date registerDate = new Date();
    private double credit;
    private UserStatus status = UserStatus.NEW;
    @ManyToMany
    private Set<Role> roles;
}

