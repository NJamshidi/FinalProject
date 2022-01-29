package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
public class Customer extends User {
    private UserStatus customerStatus = UserStatus.NEW;

    @CreationTimestamp
    private Date registerDate;

    private Double credit = 0.0;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<UserFeedback> userFeedbacks = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new HashSet<>();
        }
        orders.add(order);
        order.setCustomer(this);
    }

    public void addUserFeedback(UserFeedback userFeedback) {
        if (userFeedbacks == null) {
            userFeedbacks = new HashSet<>();
        }
        userFeedbacks.add(userFeedback);
        userFeedback.setCustomer(this);
    }

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new HashSet<>();
        }
        transactions.add(transaction);
        transaction.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerStatus=" + customerStatus +
                ", registerDate=" + registerDate +
                ", credit=" + credit +
                ", orders=" + orders +
                ", userFeedbacks=" + userFeedbacks +
                ", transactions=" + transactions +
                '}';
    }
}
