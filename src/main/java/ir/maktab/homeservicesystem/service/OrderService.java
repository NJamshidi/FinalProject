package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OrderDao;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final CustomerService customerService;
    private final SubServiceService subServiceService;

    public Order findOrderById(int id) {
        return orderDao.getById(id);
    }

    //    @Autowired
//    public OrderService(OrderDao orderDao) {
//        this.orderDao = orderDao;
//    }
//    @PostConstruct
//    public void init() {
//        setJpaRepository(orderDao);
//    }
    @Transactional
    public OrderUpdateResult acceptOffer(OfferAcceptParam offerAcceptParam) {
        Order order = orderDao.getById(offerAcceptParam.getOrderId());
        order.getOffer().forEach(o -> {
            if (o.getId() == offerAcceptParam.getAcceptedOfferId()) {
                order.acceptOffer(o);
                order.setStatus(OrderStatus.ON_GOING);
            }
        });

        Order orderResult = orderDao.save(order);
        return OrderUpdateResult.builder()
                .id(orderResult.getId())
                .successFull(true)
                .build();
    }

    @Transactional
    public OrderCreateResult saveOrder(OrderCreateEntity orderCreateEntity) {
        Customer customer = customerService.findCustomerById(orderCreateEntity.getCustomerId());
        SubService subService = subServiceService.findSubServiceById(orderCreateEntity.getSubServiceId());
        Order order = orderCreateEntity.toEntity(customer,subService);
        order.setStatus(OrderStatus.UNDER_OFFERING);
        Order saveOrder = orderDao.save(order);
        return  OrderCreateResult.builder()
                .orderId(saveOrder.getId())
                .build();
    }


    @Transactional
    public OrderUpdateResult finishOrder(int id) {
        Order order = orderDao.getById(id);
        order.setStatus(OrderStatus.DONE);
        Order orderResult = orderDao.save(order);
        return OrderUpdateResult.builder()
                .id(orderResult.getId())
                .successFull(true)
                .build();
    }


    public List<OrderCreateDto> findAllOrderByCustomerId(int customerId) {
        List<Order> orders = orderDao.findAllByCustomer_Id(customerId);
        List<OrderCreateDto> orderDtos = new ArrayList<>();
        orders.forEach(o -> orderDtos.add(new OrderCreateDto().toDto(o)));
        return orderDtos;
    }
}

