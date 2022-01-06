package com.project.homeservicesystem.entities.users;

import com.project.homeservicesystem.entities.services.MainService;
import com.project.homeservicesystem.entities.services.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
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
    @ManyToMany(mappedBy = "providers",fetch = FetchType.EAGER)
    private Set<ServiceCategory> services = new HashSet<>();

    @Override
    public String toString() {
        return super.toString() +
                "score=" + score +
                '}';
    }
}
