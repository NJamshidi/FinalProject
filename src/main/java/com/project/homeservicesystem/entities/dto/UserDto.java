package com.project.homeservicesystem.entities.dto;

import com.project.homeservicesystem.enumaration.Role;
import com.project.homeservicesystem.enumaration.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private Date registerDate;
    private double credit;
    private UserStatus status;
    private Role role;
}
