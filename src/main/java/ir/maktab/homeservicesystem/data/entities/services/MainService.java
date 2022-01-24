package ir.maktab.homeservicesystem.data.entities.services;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@SuperBuilder
public class MainService extends Service {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mainService")
    private Set<SubService> subService = new HashSet<>();

    public MainService(int id, String name, Set<SubService> subService) {
        super(id, name);
        this.subService = subService;
    }

    public MainService(String name, Set<SubService> subService) {
        super(name);
        this.subService = subService;
    }

    public MainService(Set<SubService> subService) {
        this.subService = subService;
    }


    public void addSubService(SubService subService1) {
        if (subService == null) {
            subService = new HashSet<>();
        }
        subService.add(subService1);
        subService1.setMainService(this);
    }

    @Override
    public String toString() {
        return super.toString() +
                "subService=" + subService +
                '}';
    }
}
