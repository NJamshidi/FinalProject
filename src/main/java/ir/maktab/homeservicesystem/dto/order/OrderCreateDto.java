package ir.maktab.homeservicesystem.dto.order;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateDto {
    private int id;
    private int customerId;
    private int subServiceId;
    private double price;
    private String description;
    private Date createDate;
    private Date doDate;
    private OrderStatus status;

    public OrderCreateDto toDto(Order order) {
        return OrderCreateDto.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .subServiceId(order.getSubService().getId())
                .price(order.getPrice())
                .description(order.getDescription())
                .createDate(order.getCreateDate())
                .doDate(order.getDoDate())
                .status(order.getStatus())
                .build();
    }
}