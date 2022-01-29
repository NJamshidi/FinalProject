package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OfferDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.offer.OfferMapper;
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
    private OfferMapper offerMapper;
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

    public List<OfferDto> loadByOrderIdSortAsc(int orderId) {
        List<Offer> offers = offerDao.findAllByOrder_IdOrderByPriceAsc(orderId);

        List<OfferDto> offerDtos = new ArrayList<>();
        offers.forEach(o -> offerDtos.add(offerMapper.toDto(o)));
        return offerDtos;
    }
    public List<OfferDto> loadByExpertId(int expertId) {
        List<Offer> offers = offerDao.findAllByExpert_Id(expertId);

        List<OfferDto> offerDtos = new ArrayList<>();
        offers.forEach(o -> offerDtos.add(offerMapper.toDto(o)));
        return offerDtos;
    }

    @Transactional
    public OfferDto sendOffer(OfferDto offerDto) {
        Expert expert = expertService.findById(offerDto.getId());
        Set<SubService> expertSubService = expert.getSubService();
        Order order = orderService.findById(offerDto.getId());
        SubService orderSubService = order.getSubService();
        if (!expertSubService.contains(orderSubService)) {
            throw new OfferException("The expert not have expertise");
        }
        double expertSuggestedPrice = offerDto.getPrice();
        double orderSuggestedPrice = order.getPrice();
        if (expertSuggestedPrice < orderSuggestedPrice) {
            throw new OfferException("expert suggestion price is less than order suggestion price");
        }
        Offer offer = offerMapper.toEntity(offerDto);
        order.setStatus(OrderStatus.UNDER_SELECTION); //wait for expert selection
        order.addOffer(offer); //get list and add
        offer.setOrder(order);
        Offer saveResult=offerDao.save(offer);
        return offerMapper.toDto(saveResult);
    }
  /*  public List<Offer> findByOrder(Order order) {
        return offerDao.findByOrder(order, Sort.by("expert.rate", "price").descending());
    }

    public Offer findByOrderAndExpert(Order order, Expert expert) {
        Optional<Offer> offer = offerDao.findByOrderAndExpert(order, expert);
        return offer.orElseThrow(() -> new NotFoundObjectException(("offer not found!"));
    }*/

}
