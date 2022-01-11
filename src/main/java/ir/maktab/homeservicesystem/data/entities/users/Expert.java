package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.*;


import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
//@Builder
public class Expert extends User {
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private Double credit = 0.0;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<SubService> subService= new HashSet<>();;
    @OneToMany(mappedBy = "expert", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL)
    private Set<UserFeedback> userFeedbacks;

    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL)
    private Set<Transaction> transaction;
    private UserStatus expertStatus;

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
