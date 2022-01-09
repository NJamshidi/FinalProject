package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import lombok.*;

import javax.persistence.*;
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
    private long score;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<SubService> subServices= new HashSet<>();;
    @OneToMany(mappedBy = "experts", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();

//    @OneToMany(mappedBy = "experts", cascade = CascadeType.ALL)
//    private Set<Review> reviews;
//
//    @OneToMany(mappedBy = "proficient", cascade = CascadeType.ALL)
//    private Set<Transaction> transactions;

    @Override
    public String toString() {
        return super.toString() +
                "score=" + score +
                '}';
    }
}
