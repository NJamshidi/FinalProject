package ir.maktab.homeservicesystem.data.dto;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class ServiceRequestDto {
    private Long id;
    private double price;
    private String description;
    private Date submitDate;
    private Date startDate;
    private String address;
    private SubService subService;
    private OrderStatus status ;
    private Customer customer;
}
