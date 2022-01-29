package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.CustomerDao;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordEntity;
import ir.maktab.homeservicesystem.dto.user.customer.*;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordResult;
import ir.maktab.homeservicesystem.exception.DuplicateInformationException;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerDao customerDao;
    private Validation validation = new Validation();
    //    @Autowired
//    public CustomerService(CustomerDao customerDao) {
//        this.customerDao = customerDao;
//    }
//
//    @PostConstruct
//    public void init() {
//        setJpaRepository(customerDao);
//    }

    public CustomerCreateResult saveCustomer(CustomerCreateEntity customerCreateEntity) {
        Customer customer = customerCreateEntity.toEntity();
        Customer foundedByEmail = findCustomerByEmail(customer.getEmail());
        if (foundedByEmail != null) {
            throw new DuplicateInformationException("this email used with another customer");
        }

        if (validation.validPassword(customer.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setUserRole(UserRole.CUSTOMER);
        customer.setCustomerStatus(UserStatus.NEW);
        customer.setCredit(0.0);
        Customer saveCustomer = customerDao.save(customer);
        return new CustomerCreateResult(saveCustomer.getId());
    }

    public CustomerUpdateResult updateCustomer(CustomerUpdateEntity customerUpdateEntity) {
        Customer customer = customerUpdateEntity.toEntity();
        Customer foundedById = customerDao.getById(customer.getId());
        if (!foundedById.getPassword().equals(customer.getPassword())) {
            throw new IncorrectInformationException("password Wrong!");
        }
        Customer foundedByEmail = findCustomerByEmail(customer.getEmail());
        if (foundedByEmail != null && !Objects.equals(foundedByEmail.getId(), customer.getId())) {
            throw new DuplicateInformationException("this email used with another customer");
        }
        customer.setUserRole(UserRole.CUSTOMER);
        customer.setCustomerStatus(UserStatus.UNDER_APPROVAL);
        Customer customerUpdate = customerDao.save(customer);
        return CustomerUpdateResult.builder().id(customerUpdate.getId()).successFull(true).build();
    }

    public Customer findCustomerByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Transactional
    public UserChangePasswordResult changePassword(UserChangePasswordEntity userChangePasswordEntity) {
        int customerId = userChangePasswordEntity.getUserId();
        String oldPassword = userChangePasswordEntity.getCurrentPassword();
        String newPassword = userChangePasswordEntity.getNewPassword();
        String confirmNewPass = userChangePasswordEntity.getNewPasswordConfirm();
        if (!newPassword.equals(confirmNewPass)) {
            throw new IncorrectInformationException("New password and confirm password doesn't match");
        }
        Customer customer = customerDao.getById(customerId);
        if (!Objects.equals(customer.getPassword(), oldPassword)) {
            throw new IncorrectInformationException("Old password is incorrect");
        }
        if (validation.validPassword(newPassword)) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setPassword(newPassword);
        Customer customerChangePass = customerDao.save(customer);
        return new UserChangePasswordResult(customerChangePass.getId(), customerChangePass.getPassword());
    }

    @Transactional(readOnly = true)
    public Customer findCustomerById(int customerId) {
        return customerDao.getById(customerId);
    }

    public CustomerCreateDto findCustomerByIdReturnDto(int customerId) {
        Customer customer = customerDao.getById(customerId);
        return new CustomerCreateDto().toDto(customer);
    }


    public CustomerList findAllCustomers() {
        List<Customer> customerList = customerDao.findAll();
        CustomerList customerListResult = new CustomerList();
        customerList.forEach((c) -> customerListResult.addCustomerDto(new CustomerCreateDto().toDto(c)));
        return customerListResult;
    }

    public CustomerList findAllCustomersByStatus(UserStatus status) {
        List<Customer> customerList = customerDao.findAllByCustomerStatus(status);
        CustomerList customerListResult = new CustomerList();
        customerList.forEach((c) -> customerListResult.addCustomerDto(new CustomerCreateDto().toDto(c)));
        return customerListResult;
    }

}




