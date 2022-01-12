package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Customer extends User {
    private UserStatus customerStatus = UserStatus.NEW;

//    @CreationTimestamp
    private Date registerDate;

    private Double credit = 0.0;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> Orders =new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<UserFeedback> userFeedback= new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Transaction> transaction= new HashSet<>();

    @Override
    public String toString() {
        return super.toString() +
                "customerStatus=" + customerStatus +
                ", registerDate=" + registerDate +
                ", credit=" + credit +
                ", address=" + address +
                ", Orders=" + Orders +
                '}';
    }
}
