package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OrderDao;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.OrderDto;
import ir.maktab.homeservicesystem.dto.mapper.OfferAcceptParam;
import ir.maktab.homeservicesystem.dto.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseService<Order, Integer> {
    private final OrderDao orderDao;
    private OrderMapper orderMapper;
    private final CustomerService customerService;
    private final SubServiceService subServiceService;

    public Order loadById(int id) {
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
    public OrderDto acceptOffer(OfferAcceptParam orderDto) {
        Order order = orderDao.getById(orderDto.getId());
        order.getOffer().forEach(o -> {
            if (o.getId() == orderDto.getAcceptedOffer().getId()) {
                order.acceptOffer(o);
                order.setStatus(OrderStatus.ON_GOING);
            }
        });

        Order orderResult = orderDao.save(order);
        OrderDto orderDtoUpdate = orderMapper.toDto(orderResult);
        return orderDtoUpdate;
    }

    @Transactional
    public OrderDto saveOrder(OrderDto orderDto) {
//        Customer customer = customerService.loadById(orderDto.getId());
//        SubService subService = subServiceService.findById(orderDto.getId());
        Order order = orderMapper.toEntity(orderDto);
        order.setStatus(OrderStatus.UNDER_OFFERING);
        Order saveOrderResult = orderDao.save(order);
        return orderMapper.toDto(saveOrderResult);
    }


    @Transactional
    public OrderDto finishOrder(int id) {
        Order order = orderDao.getById(id);
        order.setStatus(OrderStatus.DONE);
        Order result = orderDao.save(order);
        return orderMapper.toDto(result);
    }


    public List<OrderDto> findAllByCustomerId(int id) {
        List<Order> orders = orderDao.findAllByCustomer_Id(id);
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(o -> orderDtos.add(orderMapper.toDto(o)));
        return orderDtos;
    }
}

