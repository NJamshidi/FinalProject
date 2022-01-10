package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.CustomerDao;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class CustomerService extends BaseService<Customer, Integer>{
    private CustomerDao customerDao;
    private Validation validation = new Validation();
    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @PostConstruct
    public void init() {
        setJpaRepository(customerDao);
    }
    @Override
    public Customer save(Customer customer) {
        Customer foundedByEmail = findCustomerByEmail(customer.getEmail());
        if (foundedByEmail != null) {
            throw new DuplicateInformationException("this email used with another customer");
        }

        if  (validation.validPassword(customer.getPassword())) {
            throw new  IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setCustomerStatus(UserStatus.NEW);
        return super.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer foundedByEmail = findCustomerByEmail(customer.getEmail());
        if (foundedByEmail != null && !Objects.equals(foundedByEmail.getId(), customer.getId())) {
            throw new DuplicateInformationException("this email used with another customer");
        }

        if (validation.validPassword(customer.getPassword())) {
            throw new  IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setCustomerStatus(UserStatus.UNDER_APPROVAL);
        return super.update(customer);
    }

    public Customer findCustomerByEmail(String email) {
        return customerDao.findByEmail(email);
    }
    @Transactional
    public Customer changePassword(int customerId, String oldPassword, String newPassword) {
        Customer customer = customerDao.getById(customerId);
        if (!Objects.equals(customer.getPassword(), oldPassword)) {
            throw new IncorrectInformationException("Old password is incorrect");
        }
        if (validation.validPassword(newPassword)) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setPassword(newPassword);
        return super.update(customer);
    }

//    @Transactional
//    public void UpdatePassword(String newPassword, int id) {
//        customerDao.UpdatePassword(newPassword, id);
//    }

    @Transactional
    public Customer increaseCredit(int id, Double amount) {
        Customer customer = findById(id);
        double newCredit = customer.getCredit() + amount;
        customer.setCredit(newCredit);
        return super.update(customer);
    }

    @Transactional
    public Customer decreaseCredit(int id, Double amount) {
        Customer customer = findById(id);
        double credit = customer.getCredit();
        if (credit < amount) {
            throw new NotEnoughException("Credit is not enough");
        }
        double newCredit = credit - amount;
        customer.setCredit(newCredit);
        return super.update(customer);
    }

}
