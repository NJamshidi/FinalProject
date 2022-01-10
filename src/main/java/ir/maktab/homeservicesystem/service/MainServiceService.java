package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.CustomerDao;
import ir.maktab.homeservicesystem.data.dao.MainServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;


@Service
//@RequiredArgsConstructor
public class MainServiceService extends BaseService<MainService, Integer> {
    private final MainServiceDao mainServiceDao;
    @Autowired
    public MainServiceService(MainServiceDao mainServiceDao) {
        this.mainServiceDao = mainServiceDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(mainServiceDao);
    }
}