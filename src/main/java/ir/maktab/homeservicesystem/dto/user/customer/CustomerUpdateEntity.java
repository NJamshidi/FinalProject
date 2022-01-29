package ir.maktab.homeservicesystem.dto.user.customer;

import ir.maktab.homeservicesystem.data.entities.users.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String currentPassword;
//    private AddressUpdateParam address;

    public Customer toEntity() {
        return Customer.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.currentPassword)
//                .address(address.convert2Address())
                .build();
    }
}