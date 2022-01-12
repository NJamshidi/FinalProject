package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SubServiceService subServiceService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private OrderService orderService;


    @Test
    void save() {
        Customer customer = customerService.findById(2);
        SubService subService = subServiceService.findById(1);
        Address address = customer.getAddress();
        Order order = new Order();
        order.setCustomer(customer);
        order.setSubService(subService);
        order.setPrice(10000.0);
        order.setDescription("order 2");
        order.setAddress(address);

        Order orderResult = orderService.save(order);
        System.out.println( orderResult.getId());
        assertNotNull(orderResult);
    }

    @Test
    void loadById() {
        Order order = orderService.findById(2);
        assertNotNull(order);
    }

    @Test
    void acceptOffer() {
        Order order = orderService.findById(2);
       Offer acceptedOffer = offerService.findById(2);
        order.setAcceptedOffer(acceptedOffer);
        Order orderResult = orderService.acceptOffer(order);
        assertNotNull(orderResult);
    }

}
