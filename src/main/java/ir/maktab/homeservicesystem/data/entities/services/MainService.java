package ir.maktab.homeservicesystem.data.entities.services;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainService extends Service {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mainService")
    private Set<SubService> subService = new HashSet<>();

    @Override
    public String toString() {
               return super.toString() +
                "subService=" + subService +
                '}';
    }
}
