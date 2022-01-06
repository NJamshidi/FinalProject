package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceOfferDao;
import com.project.homeservicesystem.entities.services.ServiceCategory;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.enumaration.ServiceRequestStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Set;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ServiceOfferService {
    private ServiceOfferDao serviceOfferDao;
    public void saveNewServiceOffer(ServiceOffer serviceOffer) {
        serviceOfferDao.save(serviceOffer);
    }


    public void addOfferToRequest(ServiceOffer serviceOffer) {
        Set<ServiceCategory> providerServices = serviceOffer.getProvider().getServices();
        ServiceCategory serviceCategory = serviceOffer.getServiceRequest().getServiceCategory();
        if (providerServices.contains(serviceCategory)) {
            saveNewServiceOffer(serviceOffer);
            serviceOffer.getServiceRequest().setStatus(ServiceRequestStatus.UNDER_SELECTION);
        } else {
            throw new RuntimeException("this request service is not in your selection.");
        }
    }
}
