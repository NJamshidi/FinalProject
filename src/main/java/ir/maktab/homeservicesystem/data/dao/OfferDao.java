package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.Offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OfferDao extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByOrder_IdOrderByPriceAsc(int orderId);

}
