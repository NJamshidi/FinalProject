/*
package ir.maktab.homeservicesystem.view;

import ir.maktab.homeservicesystem.config.Config;
import ir.maktab.homeservicesystem.data.builder.CustomerBuilder;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.UserStatus;
import ir.maktab.homeservicesystem.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class CustomerView {
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    CustomerService customerService = context.getBean(CustomerService.class);
    final static Scanner scanner = new Scanner(System.in);

    public void customerSignIn() {
        try {
            System.out.println("enter your email:");
            String email = scanner.nextLine();
            Customer customer = customerService.findCustomerByEmail(email);
            UserStatus userStatus = customer.getStatus();
            if (userStatus.equals(UserStatus.UNDER_APPROVAL)) {
                System.out.println("Your account is waiting to approd.");
            } else {
                System.out.println("enter your password");
                String password = scanner.nextLine();
                if (password.equals(customer.getPassword())) {
                    System.out.println("1)\n2)");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            break;
                        case 2:
                    }
                } else {
                    throw new RuntimeException("incorrect passwor!");
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void customerSignUp() {
        try {
            System.out.println("enter your info :(firstname,lastname,emailAddress,password):");
            String info = scanner.nextLine();
            String[] splitInfo = info.split(",");
            String firstname = splitInfo[0];
            String lastname = splitInfo[1];
            String emailAddress = splitInfo[2];
            String password = splitInfo[3];
            customerService.findCustomerByEmail(emailAddress);
            Customer customer = CustomerBuilder
                    .aCustomer()
                    .withFirstName(firstname)
                    .withLastName(lastname)
                    .withEmail(emailAddress)
                    .withPassword(password)
                    .withCredit(0L)
                    .withStatus(UserStatus.UNDER_APPROVAL)
                    .build();
            customerService.saveNewCustomer(customer);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
*/
