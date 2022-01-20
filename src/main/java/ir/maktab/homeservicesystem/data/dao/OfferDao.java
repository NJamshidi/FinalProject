package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferDao extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByOrder_IdOrderByPriceAsc(int orderId);

}
/*@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {
    List<Offer> findByOrder(Order order, Sort firstSort);

    Optional<Offer> findByOrderAndExpert(Order order, Expert expert);
}*/
