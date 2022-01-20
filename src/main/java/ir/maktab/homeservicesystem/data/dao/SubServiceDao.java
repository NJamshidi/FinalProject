package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubServiceDao extends JpaRepository<SubService, Integer> {
    List<SubService> findByMainServiceId(int mainServiceId);


}
