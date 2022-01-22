package ir.maktab.homeservicesystem.data.entities.users;

import ir.maktab.homeservicesystem.dto.CustomerDto;
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
    private List<CustomerDto> customers;

    public void addCustomerModel(CustomerDto customerDto) {
        if (customers == null) {
            customers = new ArrayList<>();
        }
        customers.add(customerDto);
    }
}
