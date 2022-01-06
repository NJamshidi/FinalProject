package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.CustomerDao;
import com.project.homeservicesystem.entities.users.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class CustomerService {
    private CustomerDao customerDao;
    public void saveNewCustomer(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUserNameAndPass(customer.getUserName(), customer.getPassword());
        if (foundUser.isPresent()) {
            throw new RuntimeException("customer exist");
        } else {
            customerDao.save(customer);
        }
    }

    public Customer findCustomerByUserNameAndPass(String userName, String password) {
        Optional<Customer> customer = customerDao.findByUserNameAndPass(userName, password);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new RuntimeException("customer not found");
    }
    public void deleteCustomer(Customer customer) {
        Optional<Customer> foundUser = customerDao.findByUserNameAndPass(customer.getUserName(), customer.getPassword());
        if (foundUser.isPresent()) {
            customerDao.delete(customer);
        } else {
            throw new RuntimeException("there is no customer with these info");
        }
    }

    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

}
