package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceRequestDao;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.entities.services.ServiceRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class serviceRequestService {
    private ServiceRequestDao serviceRequestDao;
    public void saveNewServiceRequest(ServiceRequest serviceRequest) {
        serviceRequestDao.save(serviceRequest);
    }
}
