package ir.maktab.homeservicesystem.data.entities;

import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String text;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Expert expert;
    @OneToOne
    private Offer offer;
    private int rate;

}
