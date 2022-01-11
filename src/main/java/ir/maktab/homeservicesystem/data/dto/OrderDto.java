package ir.maktab.homeservicesystem.data.dto;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderDto {
    private Long id;
    private double price;
    private String description;
    private Date createDate;
    private Date doDate;
    private Address address;
    private SubService subService;
    private OrderStatus status = OrderStatus.UNDER_OFFERING;
    private Customer customer;
    private Offer acceptedOffer;

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", doDate=" + doDate +
                ", address=" + address +
                ", subService=" + subService +
                ", status=" + status +
                ", customer=" + customer +
                ", acceptedOffer=" + acceptedOffer +
                '}';
    }
}
