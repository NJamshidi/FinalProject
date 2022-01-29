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
public class CustomerCreateEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Customer toEntity() {
        return Customer.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
