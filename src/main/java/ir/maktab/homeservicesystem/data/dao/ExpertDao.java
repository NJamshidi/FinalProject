package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertDao extends JpaRepository<Expert, Integer> {
    Expert findByEmail(String email);
    List<Expert> findAllByExpertStatus(UserStatus status);

    List<Expert> findAllBySubServiceId(int subServiceId);
    @Modifying
    @Query(value = "UPDATE Expert e set e.password =:password where e.id=:id")
    void UpdatePassword(@Param("password") String password, @Param("id") int id);

}
