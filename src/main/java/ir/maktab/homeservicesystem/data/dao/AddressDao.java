package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
}
