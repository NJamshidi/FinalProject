package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Expert extends User {
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private Double credit = 0.0;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<SubService> subService = new HashSet<>();
    ;
    @OneToMany(mappedBy = "expert", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL)
    private Set<UserFeedback> userFeedbacks;

    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL)
    private Set<Transaction> transaction;
    private UserStatus expertStatus;

    @Builder
    public Expert(int id, String firstName, String lastName, String email, String userName, String password, UserRole userRole, byte[] image, Double credit, Set<SubService> subService, Set<Offer> offers, Set<UserFeedback> userFeedbacks, Set<Transaction> transaction, UserStatus expertStatus) {
        super(id, firstName, lastName, email, userName, password, userRole);
        this.image = image;
        this.credit = credit;
        this.subService = subService;
        this.offers = offers;
        this.userFeedbacks = userFeedbacks;
        this.transaction = transaction;
        this.expertStatus = expertStatus;
    }

    @Override
    public String toString() {
        return super.toString() +

                "image=" + Arrays.toString(image) +
                ", credit=" + credit +
                ", subService=" + subService +
                ", offers=" + offers +
                ", userFeedbacks=" + userFeedbacks +
                ", transaction=" + transaction +
                ", expertStatus=" + expertStatus +
                '}';
    }
}
