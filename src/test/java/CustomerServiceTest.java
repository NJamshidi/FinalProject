
import com.project.homeservicesystem.config.HibernateConfig;
import com.project.homeservicesystem.entities.users.Customer;
import com.project.homeservicesystem.enumaration.Role;
import com.project.homeservicesystem.enumaration.UserStatus;
import com.project.homeservicesystem.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    CustomerService customerService = context.getBean(CustomerService.class);

    Customer customer;

    @BeforeEach
    void init() {
        customer = (Customer) Customer.builder().firstName("ahmad").lastName("rezaee").email("ahmadRezaee@gmail.com").role(Role.CUSTOMER).userName("a123")
                .password("ali@7895").status(UserStatus.UNDER_APPROVAL).credit(123).build();

    }

    @Test
    void giveDuplicateCustomer_findCustomerByUseAndPass_ThrowException() {
        customer = customerService.findCustomerByUserNameAndPass("n123", "narges@");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.saveNewCustomer(customer));
        Assertions.assertEquals("this customer is already exist", result.getMessage());
    }
    @Test
    void deleteNoExistCustomer_deleteCustomer_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.deleteCustomer(customer));
        Assertions.assertEquals("there is no customer with these info", result.getMessage());
    }

    @Test
    void NoExistCustomer_findCustomerByUseAndPass_ThrowException() {
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                customerService.findCustomerByUserNameAndPass(customer.getUserName(),customer.getPassword()));
        Assertions.assertEquals("no customer found with these use and pass", result.getMessage());
    }}