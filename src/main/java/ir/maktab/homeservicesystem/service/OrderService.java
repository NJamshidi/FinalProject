package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OrderDao;
import ir.maktab.homeservicesystem.data.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
//@RequiredArgsConstructor
public class OrderService extends BaseService<Order, Integer> {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(orderDao);
    }

}
