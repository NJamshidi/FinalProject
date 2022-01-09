package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainServiceDao extends JpaRepository<MainService, Integer> {
}
