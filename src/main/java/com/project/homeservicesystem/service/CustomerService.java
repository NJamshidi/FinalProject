package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.CustomerDao;
import com.project.homeservicesystem.entities.users.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerService {
    private CustomerDao customerDao;
    public void saveNewCustomer(Customer customer) {
        customerDao.save(customer);
    }
    public Customer findCustomerByUserNameAndPass(String userName, String password) {
        return customerDao.findByUserNameAndPass(userName,password);
    }


}
