package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedbackDao extends JpaRepository<UserFeedback, Integer> {

}
