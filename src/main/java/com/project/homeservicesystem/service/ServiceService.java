package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceDao;
import com.project.homeservicesystem.entities.services.Service;
import com.project.homeservicesystem.entities.users.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private ServiceDao serviceDao;
    public void saveNewService(Service service) {
        serviceDao.save(service);
    }

    public Service findServiceByTitle(String title) {
        return serviceDao.findByTitle(title);
    }
}
