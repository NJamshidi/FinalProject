package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OrderDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.enumaration.OfferStatus;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseService<Order, Integer> {
    private final OrderDao orderDao;

//    @Autowired
//    public OrderService(OrderDao orderDao) {
//        this.orderDao = orderDao;
//    }
//    @PostConstruct
//    public void init() {
//        setJpaRepository(orderDao);
//    }
    @Transactional
    public Order acceptOffer(Order order) {
        Offer acceptOffer = order.getAcceptedOffer();
        acceptOffer.setOfferStatus(OfferStatus.ACCEPTED);
        order.setAcceptedOffer(acceptOffer);
       order.setStatus(OrderStatus.ON_GOING);
        return super.update(order);
    }


}
