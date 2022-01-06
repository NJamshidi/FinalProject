package com.project.homeservicesystem.entities.users;

import com.project.homeservicesystem.enumaration.Role;
import com.project.homeservicesystem.enumaration.UserStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    @CreationTimestamp
    private Date registerDate;
    private double credit;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.NEW;
    @Enumerated(EnumType.STRING)
    private Role role;
}

