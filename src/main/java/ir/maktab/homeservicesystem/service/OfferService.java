package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OfferDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.offer.OfferCreateDto;
import ir.maktab.homeservicesystem.dto.offer.OfferCreateEntity;
import ir.maktab.homeservicesystem.dto.offer.OfferCreateResult;
import ir.maktab.homeservicesystem.exception.OfferException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OfferService extends BaseService<Offer, Integer> {
    private final OfferDao offerDao;
    private final OrderService orderService;
    private final ExpertService expertService;

    //    @Autowired
//    public OfferService(OfferDao offerDao, OrderService orderService) {
//        this.offerDao = offerDao;
//        this.orderService = orderService;
//    }
    @PostConstruct
    public void init() {
        setJpaRepository(offerDao);
    }

    public List<OfferCreateDto> findOfferByOrderIdSortAsc(int orderId) {
        List<Offer> offers = offerDao.findAllByOrder_IdOrderByPriceAsc(orderId);

        List<OfferCreateDto> offerDtos = new ArrayList<>();
        offers.forEach(o -> offerDtos.add(new OfferCreateDto().toDto(o)));
        return offerDtos;
    }

    public List<OfferCreateDto> findOfferByExpertId(int expertId) {
        List<Offer> offers = offerDao.findAllByExpert_Id(expertId);

        List<OfferCreateDto> offerDtos = new ArrayList<>();
        offers.forEach(o -> offerDtos.add(new OfferCreateDto().toDto(o)));
        return offerDtos;
    }

    @Transactional
    public OfferCreateResult sendOffer(OfferCreateEntity offerCreateEntity) {
        Expert expert = expertService.findExpertById(offerCreateEntity.getExpertId());
        Set<SubService> expertSubService = expert.getSubService();
        Order order = orderService.findOrderById(offerCreateEntity.getOrderId());
        SubService orderSubService = order.getSubService();
        if (!expertSubService.contains(orderSubService)) {
            throw new OfferException("The expert not have expertise");
        }
        double expertSuggestedPrice = offerCreateEntity.getPrice();
        double orderSuggestedPrice = order.getPrice();
        if (expertSuggestedPrice < orderSuggestedPrice) {
            throw new OfferException("expert suggestion price is less than order suggestion price");
        }
        Offer offer = offerCreateEntity.toEntity(order,expert);
        order.setStatus(OrderStatus.UNDER_SELECTION); //wait for expert selection
        order.addOffer(offer); //get list and add
        offer.setOrder(order);
        Offer saveOffer=offerDao.save(offer);
        return OfferCreateResult.builder()
                .offerId(saveOffer.getId())
                .build();
    }
  /*  public List<Offer> findByOrder(Order order) {
        return offerDao.findByOrder(order, Sort.by("expert.rate", "price").descending());
    }

    public Offer findByOrderAndExpert(Order order, Expert expert) {
        Optional<Offer> offer = offerDao.findByOrderAndExpert(order, expert);
        return offer.orElseThrow(() -> new NotFoundObjectException(("offer not found!"));
    }*/

}
