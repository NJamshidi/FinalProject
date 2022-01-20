package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);

    Admin findByUserNameAndPassword(String userName, String password);

    @Modifying
    @Query(value = "UPDATE Admin a set a.password =:password where a.id=:id")
    void UpdatePassword(@Param("password") String password, @Param("id") int id);
}

