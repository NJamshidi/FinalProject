package com.project.homeservicesystem.entities.users;

import com.project.homeservicesystem.entities.services.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Provider extends User {
    private String profilePhotoUrl;
    private long score;
    @ManyToMany
    private Set<Service> services;
}
