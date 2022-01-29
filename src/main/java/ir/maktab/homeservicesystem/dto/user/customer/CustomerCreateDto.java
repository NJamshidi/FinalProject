package ir.maktab.homeservicesystem.dto.user.customer;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreateDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserStatus customerStatus = UserStatus.NEW;
    private Date registerDate;
    private Double credit ;
    private UserRole userRole;
//    private AddressCreateDto addressCreateDto;

    public CustomerCreateDto toDto(Customer customer) {
        return CustomerCreateDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(customer.getCredit())
                .customerStatus(customer.getCustomerStatus())
                .userRole(customer.getUserRole())
                .registerDate(customer.getRegisterDate())
//                .addressModel(new AddressModel().convertAddress2Model(customer.getAddress()))
                .build();
    }
}
