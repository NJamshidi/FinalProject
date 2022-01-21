package ir.maktab.homeservicesystem.dto.mapper;


import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.dto.CustomerDto;
import org.springframework.stereotype.Component;

import static ir.maktab.homeservicesystem.dto.CustomerDto.*;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .userName(customer.getUserName())
                .password(customer.getPassword())
                .registerDate(customer.getRegisterDate())
                .credit(customer.getCredit())
                .customerStatus(customer.getCustomerStatus())
                .address(customer.getAddress())
                .build();
    }
    public Customer toEntity(CustomerDto customerDto){
        return  Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .userName(customerDto.getUserName())
                .password(customerDto.getPassword())
                .registerDate(customerDto.getRegisterDate())
                .credit(customerDto.getCredit())
                .customerStatus(customerDto.getCustomerStatus())
                .address(customerDto.getAddress())
                .build();
    }
}
