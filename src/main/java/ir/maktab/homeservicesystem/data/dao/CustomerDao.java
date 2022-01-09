package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.users.Admin;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
      Customer findByEmail(String email);
    Customer findByUserNameAndPassword(String userName, String password);

        @Modifying
        @Query(value = "UPDATE Customer c set c.password =:password where c.id=:id")
        void UpdatePassword(@Param("password") String password, @Param("id") int id);
    }

