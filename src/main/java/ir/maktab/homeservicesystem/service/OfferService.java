package ir.maktab.homeservicesystem.service;
import ir.maktab.homeservicesystem.data.dao.OfferDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import ir.maktab.homeservicesystem.exception.OfferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class OfferService extends BaseService<Offer, Integer> {
    private final OfferDao offerDao;
    private final OrderService orderService;
    @Autowired
    public OfferService(OfferDao offerDao, OrderService orderService) {
        this.offerDao = offerDao;
        this.orderService = orderService;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(offerDao);
    }

    public List<Offer> loadByOrderIdSortAsc(int orderId) {
        return offerDao.findAllByOrder_IdOrderByPriceAsc(orderId);
    }
    @Transactional
    public Offer sendOffer(Offer offer) {
        Set<SubService> expertSubService = offer.getExpert().getSubService();
        Order order = offer.getOrder();
        SubService orderSubService = order.getSubService();
        if (!expertSubService.contains(orderSubService)) {
            throw new OfferException("The expert not have expertise");
        }
        double expertSuggestedPrice = offer.getPrice();
        double orderSuggestedPrice = order.getPrice();
        if (expertSuggestedPrice < orderSuggestedPrice) {
            throw new OfferException("expert suggestion price is less than order suggestion price");
        }
        order.setStatus(OrderStatus.UNDER_SELECTION); //wait for expert selection
        order.addOffer(offer); //get list and add
        orderService.update(order);
        offer.setOrder(order);
        return super.save(offer);
    }
  /*  public List<Offer> findByOrder(Order order) {
        return offerDao.findByOrder(order, Sort.by("expert.rate", "price").descending());
    }

    public Offer findByOrderAndExpert(Order order, Expert expert) {
        Optional<Offer> offer = offerDao.findByOrderAndExpert(order, expert);
        return offer.orElseThrow(() -> new NotFoundObjectException(("offer not found!"));
    }*/

}
