package ir.maktab.homeservicesystem.data.entities;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OfferStatus;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @CreationTimestamp
    private Date submitOfferDate;
    @Temporal(TemporalType.TIME)
    private Date startWorkTime;
    private double price;
    private double durationInHours;
    @ManyToOne
    @JoinColumn(nullable = false ,foreignKey = @ForeignKey(name = "FK_OFFER_ORDER"))
    private Order order;
    @ManyToOne
    @JoinColumn(nullable = false,foreignKey = @ForeignKey(name = "FK_OFFER_EXPERT"))
    private Expert expert;
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", submitOfferDate=" + submitOfferDate +
                ", startWorkTime=" + startWorkTime +
                ", price=" + price +
                ", durationInHours=" + durationInHours +
                ", order=" + order +
                ", expert=" + expert +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
