package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomer_Id(int id);
}
