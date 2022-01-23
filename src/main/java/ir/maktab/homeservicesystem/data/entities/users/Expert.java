package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
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
    @Builder.Default
    private double ratingAvg = 0.0;

    public void addOffer(Offer offer) {
        if (offers == null) {
            offers = new HashSet<>();
        }
       offers.add(offer);
       offer.setExpert(this);
    }

    public void addUserFeedback(UserFeedback userFeedback) {
        if (userFeedbacks == null) {
            userFeedbacks = new HashSet<>();
        }
        userFeedbacks.add(userFeedback);
        double ratingSum = userFeedbacks.stream().mapToDouble(UserFeedback::getRate).sum();

        DecimalFormat df = new DecimalFormat("0.00");
        ratingAvg = Double.parseDouble(df.format(ratingSum / userFeedbacks.size()));
        userFeedback.setExpert(this);
    }

    public void addTransaction(Transaction transaction1) {
        if (transaction == null) {
            transaction = new HashSet<>();
        }
        transaction.add(transaction1);
        transaction1.setExpert(this);
    }

    public void addSubService(SubService subService1) {
        if (subService == null) {
            subService = new HashSet<>();
        }
        subService.add(subService1);
    }

    public void removeSubService(SubService subService1) {
        if (subService == null) {
            throw new NotFoundObjectException("subService ", subService1.getId());
        }
        subService.remove(subService1);
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
