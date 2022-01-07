package com.project.homeservicesystem.entities.dto;

import com.project.homeservicesystem.entities.services.ServiceCategory;
import com.project.homeservicesystem.enumaration.Role;
import com.project.homeservicesystem.enumaration.UserStatus;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
public class ProviderDto {
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
    private byte[] image;
    private long score;
}
