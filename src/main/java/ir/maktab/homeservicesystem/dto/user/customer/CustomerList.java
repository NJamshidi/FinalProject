package ir.maktab.homeservicesystem.dto.user.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerList {
    private List<CustomerCreateDto> customers;

    public void addCustomerDto(CustomerCreateDto customerCreateDto) {
        if (customers == null) {
            customers = new ArrayList<>();
        }
        customers.add(customerCreateDto);
    }
}
