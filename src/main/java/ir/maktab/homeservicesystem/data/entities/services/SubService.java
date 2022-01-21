package ir.maktab.homeservicesystem.data.entities.services;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class SubService extends Service {
    private double basePrice;
    @Lob
    private String description;
    @ManyToOne
    private MainService mainService;
    @ManyToMany(mappedBy = "subService")
    private Set<Expert> expert = new HashSet<>();
    @OneToMany(mappedBy = "subService")
    private Set<Order> Order = new HashSet<>();
@Builder
    public SubService(int id, String name, double basePrice, String description, MainService mainService, Set<Expert> expert, Set<ir.maktab.homeservicesystem.data.entities.Order> order) {
        super(id, name);
        this.basePrice = basePrice;
        this.description = description;
        this.mainService = mainService;
        this.expert = expert;
        Order = order;
    }

    @Override
    public String toString() {
        return super.toString() +
                "basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", mainService=" + mainService +
                ", expert=" + expert +
                ", Order=" + Order +
                '}';
    }
}
