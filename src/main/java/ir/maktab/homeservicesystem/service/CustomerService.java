package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.CustomerDao;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.dto.user.customer.CustomerList;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.dto.CustomerDto;
import ir.maktab.homeservicesystem.dto.mapper.UserChangePasswordParam;
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
    CustomerMapper customerMapper = new CustomerMapper();

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer foundedByEmail = findCustomerByEmail(customer.getEmail());
        if (foundedByEmail != null) {
            throw new DuplicateInformationException("this email used with another customer");
        }

        if (validation.validPassword(customerDto.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setCustomerStatus(UserStatus.NEW);
        customer.setCredit(0.0);
        Customer saveCustomer = customerDao.save(customer);
        CustomerDto customerDtoSave = customerMapper.toDto(saveCustomer);
        return customerDtoSave;
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer foundedById = customerDao.getById(customer.getId());
        if (!foundedById.getPassword().equals(customer.getPassword())) {
            throw new IncorrectInformationException("password Wrong!");
        }
        Customer foundedByEmail = loadByEmail(customer.getEmail());
        if (foundedByEmail != null && !Objects.equals(foundedByEmail.getId(), customer.getId())) {
            throw new DuplicateInformationException("this email used with another customer");
        }
        customer.setUserRole(UserRole.CUSTOMER);
        customer.setCustomerStatus(UserStatus.UNDER_APPROVAL);
        Customer customerUpdate = customerDao.save(customer);
        CustomerDto customerDtoUpdate = customerMapper.toDto(customerUpdate);
        return customerDtoUpdate;
    }

    public Customer findCustomerByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    @Transactional
    public UserChangePasswordResult changePassword(UserChangePasswordParam userChangePasswordParam) {
        int customerId = userChangePasswordParam.getUserId();
        String oldPassword = userChangePasswordParam.getCurrentPassword();
        String newPassword = userChangePasswordParam.getNewPassword();
        String confirmNewPass = userChangePasswordParam.getNewPasswordConfirm();
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
    public Customer loadById(int customerId) {
        return customerDao.getById(customerId);
    }

    public CustomerDto loadByIdReturnDto(int customerId) {
        Customer customer = customerDao.getById(customerId);
        CustomerDto customerDto = customerMapper.toDto(customer);
        return customerDto;
    }

    private Customer loadByEmail(String email) {
        return customerDao.findByEmail(email);
    }

    public CustomerList loadAllCustomers() {
        List<Customer> customerList = customerDao.findAll();
        CustomerList customerListResult = new CustomerList();
        customerList.forEach((c) -> customerListResult.addCustomerDto(customerMapper.toDto(c)));
        return customerListResult;
    }

    public CustomerList loadAllCustomersByStatus(UserStatus status) {
        List<Customer> customerList = customerDao.findAllByCustomerStatus(status);
        CustomerList customerListResult = new CustomerList();
        customerList.forEach((c) -> customerListResult.addCustomerDto(customerMapper.toDto(c)));
        return customerListResult;
    }

}




