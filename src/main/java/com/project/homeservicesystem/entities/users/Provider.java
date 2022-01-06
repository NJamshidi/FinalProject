package com.project.homeservicesystem.entities.users;

import com.project.homeservicesystem.entities.services.MainService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Provider extends User {
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private long score;
    @ManyToMany
    private Set<MainService> mainServices;
}
