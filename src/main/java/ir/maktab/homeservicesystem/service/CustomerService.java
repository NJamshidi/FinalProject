package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.users.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Customer findCustomerByEmail(String email) {
        Optional<Customer> customer = customerDao.findByEmail( email);
        if (customer.isPresent()) {
            return customer.get();
        } else
            throw new RuntimeException("customer not found");
    }
@Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
