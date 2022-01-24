package ir.maktab.homeservicesystem.data.entities.services;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
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
    public void addExpert(Expert expert1) {
        if (expert == null) {
            expert = new HashSet<>();
        }
        expert.add(expert1);

        expert1.addSubService(this);
    }

    public void removeExpert(Expert expert1){
        if (expert == null) {
            throw new NotFoundObjectException("expert ", expert1.getId());
        }
        expert.remove(expert1);
        expert1.removeSubService(this);
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
