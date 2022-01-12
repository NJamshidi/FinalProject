package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private OfferService offerService;

    @Test
    void save() {
        Offer offer = offerService.findById(2);
        Order order = offer.getOrder();
        Expert expert = offer.getExpert();
        Customer customer = order.getCustomer();
        Double price = offer.getPrice();
        Transaction transaction = new Transaction();
        transaction.setAmount(price);
        transaction.setCustomer(customer);
        transaction.setExpert(expert);
        transaction.setOffer(offer);
        transaction.setOrder(order);
        Transaction transactionResult = transactionService.save(transaction);
        assertNotNull(transactionResult);
    }
}
