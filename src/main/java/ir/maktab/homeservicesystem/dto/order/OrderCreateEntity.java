package ir.maktab.homeservicesystem.dto.order;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.dto.address.AddressCreateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateEntity {
    private int customerId;
    private int subServiceId;
    private double price;
    private String description;
    private AddressCreateEntity address;

    public Order toEntity(Customer customer, SubService subService) {
        return Order.builder()
                .customer(customer)
                .subService(subService)
                .price(this.price)
                .description(this.description)
                .address(address.toEntity())
                .build();
    }
}