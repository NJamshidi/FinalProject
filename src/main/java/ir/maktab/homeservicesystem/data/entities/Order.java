package ir.maktab.homeservicesystem.data.entities;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private String description;
    @CreationTimestamp
    private Date createDate;
    private Date finishDate;
    @ManyToOne
    private Address address;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SubService subService;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.UNDER_OFFERING;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Offer> offer;
    @OneToOne(cascade = CascadeType.ALL)
    private Offer acceptedOffer;

    public void addOffer(Offer offer) {
        this.offer.add(offer);
    }
}
