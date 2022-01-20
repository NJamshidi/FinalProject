package ir.maktab.homeservicesystem.data.entities.services;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
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
