package com.project.homeservicesystem.entities.builder;

import com.project.homeservicesystem.entities.users.Customer;
import com.project.homeservicesystem.entities.users.Role;
import com.project.homeservicesystem.enumaration.UserStatus;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

public final class CustomerBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date registerDate;
    private double credit;
    private UserStatus status;


    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder withRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public CustomerBuilder withCredit(Long credit) {
        this.credit = credit;
        return this;
    }

    public CustomerBuilder withStatus(UserStatus status) {
        this.status = UserStatus.NEW;
        return this;
    }


    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setRegisterDate(registerDate);
        customer.setCredit(credit);
        customer.setStatus(status);
        return customer;
    }
}