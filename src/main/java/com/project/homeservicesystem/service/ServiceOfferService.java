package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceOfferDao;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.entities.users.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ServiceOfferService {
    private ServiceOfferDao serviceOfferDao;
    public void saveNewServiceOffer(ServiceOffer serviceOffer) {
        serviceOfferDao.save(serviceOffer);
    }
}
