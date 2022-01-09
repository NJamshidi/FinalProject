package ir.maktab.homeservicesystem.data.entities.services;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
