package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.exception.NotEnoughException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    void save() {
        Customer customer = new Customer();
        customer.setFirstName("asal");
        customer.setLastName("ahmadi");
        customer.setUserName("asal");
        customer.setEmail("ahmadi@gmail.com");
        customer.setPassword("12345678asd");
        customer.setCredit(50000d);
        Customer result = customerService.save(customer);
        assertNotNull(result);
    }
    @Test
    void loadById(){
        Customer customer = customerService.findById(1);
        System.out.println( customer.getAddress().getPlaque());
    }
    @Test
    void changePass() {
        Customer customer = customerService.changePassword(1, "12345678asd", "456asd");
        assertEquals("456asd", customer.getPassword());
    }

    @Test
    void changePasswordWithIncorrectOldPass() {
        assertThrows(IncorrectInformationException.class, () ->
                customerService.changePassword(1, "123yy4", "45yyy78")
        );
    }

    @Test
    void changePasswordWithIncorrectNewPass() {
        assertThrows(IncorrectInformationException.class, () ->
                customerService.changePassword(1, "456asd", "1")
        );
    }



    @Test
    void increaseCredit() {
        Customer customer = customerService.increaseCredit(1, 5000.0);
        assertEquals(55000.0, customer.getCredit());
    }

    @Test
    void decreaseCreditOk() {
        Customer customer = customerService.decreaseCredit(1, 5000.0);
        assertEquals(10000.0, customer.getCredit());
    }

    @Test
    void decreaseCreditNotEnough() {
        assertThrows(NotEnoughException.class, () -> customerService.decreaseCredit(1, 15000.0));
    }

}
