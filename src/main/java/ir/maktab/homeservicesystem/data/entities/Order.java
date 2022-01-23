package ir.maktab.homeservicesystem.data.entities;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    @Column(length = 250)
    private String description;
    //    @CreationTimestamp
    private Date createDate;
    @Temporal(TemporalType.DATE)
    private Date doDate;
    @ManyToOne
    private Address address;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SubService subService;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.UNDER_OFFERING;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Offer> offer = new HashSet<>();
    ;
    @OneToOne(cascade = CascadeType.ALL)
    private Offer acceptedOffer;

    public void addOffer(Offer offer1) {
        if (offer == null) {
            offer = new HashSet<>();
        }
        offer.add(offer1);
        offer1.setOrder(this);
    }

    public void acceptOffer(Offer offer1) {
        if (offer.contains(offer1)) {
            acceptedOffer = offer1;
            offer1.setIsAccepted(true);
        }
    }

}
