package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Builder
public class UserFeedbackDto {
    private int id;
    private String text;
    private Customer customer;
    private Expert expert;
    private Offer offer;
    private int rate;

    public UserFeedbackDto(int id, String text, Customer customer, Expert expert, Offer offer, int rate) {
        this.id = id;
        this.text = text;
        this.customer = customer;
        this.expert = expert;
        this.offer = offer;
        this.rate = rate;
    }
}
