package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceOfferDao;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.entities.users.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceOfferService {
    private ServiceOfferDao serviceOfferDao;
    public void saveNewServiceOffer(ServiceOffer serviceOffer) {
        serviceOfferDao.save(serviceOffer);
    }
}
