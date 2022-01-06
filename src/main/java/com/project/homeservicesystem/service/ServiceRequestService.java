package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceRequestDao;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.entities.services.ServiceRequest;
import com.project.homeservicesystem.enumaration.ServiceOfferStatus;
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
public class ServiceRequestService {
    private ServiceRequestDao serviceRequestDao;

    public void saveNewServiceRequest(ServiceRequest serviceRequest) {
        serviceRequestDao.save(serviceRequest);
    }

    public ServiceRequest findById(Integer id) {return serviceRequestDao.findServiceRequestByID(id);}

    public ServiceOffer findAcceptOfferOfRequest(ServiceRequest serviceRequest) {
        ServiceOffer acceptOffer = null;
        if (serviceRequest.getStatus().equals(ServiceRequestStatus.PAID)) {
            Set<ServiceOffer> serviceOffers = serviceRequest.getOffers();
            for (ServiceOffer serviceOffer : serviceOffers) {
                if (serviceOffer.getServiceOfferStatus().equals(ServiceOfferStatus.ACCEPTED)) {
                    acceptOffer = serviceOffer;
                }
            }
            return acceptOffer;
        } else {
            throw new RuntimeException("not paid!");
        }
    }
}
