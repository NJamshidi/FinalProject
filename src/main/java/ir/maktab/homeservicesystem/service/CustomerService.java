package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.CustomerDao;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.dto.CustomerDto;
import ir.maktab.homeservicesystem.dto.mapper.CustomerMapper;
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
    public CustomerDto changePassword(CustomerDto customerDto, String oldPassword, String newPassword) {
        int id = customerDto.getId();
        Customer customer = customerDao.getById(id);
        if (!Objects.equals(customer.getPassword(), oldPassword)) {
            throw new IncorrectInformationException("Old password is incorrect");
        }
        if (validation.validPassword(newPassword)) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        customer.setPassword(newPassword);
        Customer customerChangePass = customerDao.save(customer);
        CustomerDto customerDtoUpdatePass = customerMapper.toDto(customerChangePass);
        return customerDtoUpdatePass;
    }

    @Transactional(readOnly = true)
    public Customer loadById(int customerId) {
        return customerDao.getById(customerId);
    }

    private Customer loadByEmail(String email) {
        return customerDao.findByEmail(email);
    }
    public CustomerModel loadByIdReturnModel(int customerId) {
        Customer customer = customerDao.getById(customerId);
        return new CustomerDto.convertCustomer2Model(customer);
    }
    public CustomerListResult findAllCustomers() {
        List<Customer> customerList = customerDao.findAll();
        CustomerListResult customerListResult = new CustomerListResult();
        customerList.forEach((c) -> customerListResult.addCustomerModel(new CustomerModel().convertCustomer2Model(c)));
        return customerListResult;
    }

    public CustomerListResult loadAllCustomersByStatus(UserStatus status) {
        List<Customer> customerList = customerRepository.findAllByCustomerStatus(status);
        CustomerListResult customerListResult = new CustomerListResult();
        customerList.forEach((c) -> customerListResult.addCustomerModel(new CustomerModel().convertCustomer2Model(c)));
        return customerListResult;
    }


}




