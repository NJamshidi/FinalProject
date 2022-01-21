package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderDto {
    private int id;
    private double price;
    private String description;
    private Date createDate;
    private Date doDate;
    private Address address;
    private SubServiceDto subServiceDto;
    private OrderStatus status = OrderStatus.UNDER_OFFERING;
    private CustomerDto customerDto;
    private Offer acceptedOffer;

    @Builder
    public OrderDto(int id, double price, String description, Date createDate, Date doDate, Address address, SubServiceDto subServiceDto, OrderStatus status, CustomerDto customerDto, Offer acceptedOffer) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.createDate = createDate;
        this.doDate = doDate;
        this.address = address;
        this.subServiceDto = subServiceDto;
        this.status = status;
        this.customerDto = customerDto;
        this.acceptedOffer = acceptedOffer;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", doDate=" + doDate +
                ", address=" + address +
                ", subService=" + subServiceDto +
                ", status=" + status +
                ", customer=" + customerDto +
                ", acceptedOffer=" + acceptedOffer +
                '}';
    }
}
