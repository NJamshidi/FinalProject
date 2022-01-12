package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

public class OfferServiceTest {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private OrderService orderService;

    @Test
    void save() {
        Offer offer = new Offer();

        Expert expert = expertService.findById(2);
        Order order = orderService.findById(1);

        offer.setExpert(expert);
        offer.setPrice(10000.0);
        offer.setOrder(order);

        Offer offerResult = offerService.save(offer);
        assertNotNull(offerResult);
    }

    @Test
    void delete() {
        offerService.removeById(1);
        assertThrows(NotFoundObjectException.class, () ->offerService.findById(1));
    }

    @Test
    void update() {
        Offer offer = offerService.findById(1);
        offer.setPrice(20000.0);
        Offer offerResult = offerService.update(offer);
        assertEquals(20000.0, offerResult.getPrice());
    }

    @Test
    void sendOffer() {
        Offer offer = new Offer();
        offer.setExpert(expertService.findById(7));
        offer.setPrice(2000.0);
        offer.setDurationInHours(2);
        offer.setOrder(orderService.findById(4));

        Offer offerResult = offerService.sendOffer(offer);
        assertNotNull(offerResult);
    }

    @Test
    void loadByOrderId() {
        List<Offer> offers = offerService.loadByOrderIdSortAsc(4);
        offers.forEach(ho -> {
            System.out.println(ho.getId() + ": " + ho.getPrice());
        });
    }
}
