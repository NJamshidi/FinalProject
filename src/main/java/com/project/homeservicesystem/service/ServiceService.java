package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceDao;
import com.project.homeservicesystem.entities.services.MainService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private ServiceDao serviceDao;
    public void saveNewService(MainService mainService) {
        serviceDao.save(mainService);
    }

    public MainService findServiceByTitle(String title) {
        return serviceDao.findByTitle(title);
    }
}
