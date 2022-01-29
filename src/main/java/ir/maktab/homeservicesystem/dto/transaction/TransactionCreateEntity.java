package ir.maktab.homeservicesystem.dto.transaction;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.Transaction;
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
public class TransactionCreateEntity {
    private int customerId;
    private int orderId;
    private String customerCreditCardNumber;

    public Transaction toEntity(Expert expert, Customer customer, Order order, Offer offer) {
        return Transaction.builder()
                .expert(expert)
                .customer(customer)
                .order(order)
                .offer(offer)
                .customerCreditCardNumber(this.customerCreditCardNumber)
                .amount(offer.getPrice())
                .build();
    }
}