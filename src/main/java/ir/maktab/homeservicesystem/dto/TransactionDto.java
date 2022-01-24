package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private int id;
    private Date createDate;
    private Double amount;
    private Expert expert;
    private Customer customer;
    private Order order;
    private Offer offer;
    private String customerCreditCardNumber;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", amount=" + amount +
                ", expert=" + expert +
                ", customer=" + customer +
                ", order=" + order +
                ", offer=" + offer +
                '}';
    }
}
