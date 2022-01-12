package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class UserFeedBackServiceTest {
    @Autowired
    private UserFeedbackService userFeedbackService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private CustomerService customerService;

    @Test
    void save() {
        UserFeedback userFeedback = new UserFeedback();
        Offer offer = offerService.findById(2);
        Expert expert = offer.getExpert();
        Customer customer = customerService.findById(1);
        userFeedback.setText("text1");
        userFeedback.setRate(4);
        userFeedback.setCustomer(customer);
        userFeedback.setOffer(offer);
        userFeedback.setExpert(expert);
        UserFeedback userFeedbackResult = userFeedbackService.save(userFeedback);
        assertNotNull(userFeedbackResult);
    }
}
