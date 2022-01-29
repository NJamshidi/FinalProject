package ir.maktab.homeservicesystem.dto.userFeedback;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFeedbackCreateEntity {
    private int customerId;
    private int orderId;
    private int rate;
    private String text;


    public UserFeedback toEntity(Expert expert, Customer customer , Offer offer) {
        return UserFeedback.builder()
                .customer(customer)
                .expert(expert)
                .offer(offer)
                .rate(this.rate)
                .text(this.text)
                .build();
    }
}