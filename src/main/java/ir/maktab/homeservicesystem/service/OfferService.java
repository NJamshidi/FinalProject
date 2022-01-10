package ir.maktab.homeservicesystem.service;
import ir.maktab.homeservicesystem.data.dao.OfferDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
//@RequiredArgsConstructor
public class OfferService extends BaseService<Offer, Integer> {
    private final OfferDao offerDao;
    @Autowired
    public OfferService(OfferDao offerDao) {
        this.offerDao = offerDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(offerDao);
    }
}
