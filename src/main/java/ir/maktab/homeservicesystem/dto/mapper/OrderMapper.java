package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapper {
    private final SubServiceMapper subServiceMapper;
    private final ExpertMapper expertMapper;
    private final CustomerMapper customerMapper;

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .price(order.getPrice())
                .description(order.getDescription())
                .createDate(order.getCreateDate())
                .doDate(order.getDoDate())
                .address(order.getAddress())
                .subServiceDto(subServiceMapper.toDto(order.getSubService()))
                .status(order.getStatus())
                .customerDto(customerMapper.toDto(order.getCustomer()))
                .acceptedOffer(order.getAcceptedOffer())
                .build();
    }

    public Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .price(orderDto.getPrice())
                .description(orderDto.getDescription())
                .createDate(orderDto.getCreateDate())
                .doDate(orderDto.getDoDate())
                .address(orderDto.getAddress())
                .subService(subServiceMapper.toEntity(orderDto.getSubServiceDto()))
                .status(orderDto.getStatus())
                .customer(customerMapper.toEntity(orderDto.getCustomerDto()))
                .acceptedOffer(orderDto.getAcceptedOffer())
                .build();
    }
}
