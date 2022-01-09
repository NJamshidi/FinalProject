package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.ServiceOfferDao;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.services.ServiceOffer;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
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
        Set<SubService> providerServices = serviceOffer.getProvider().getServices();
        SubService subService = serviceOffer.getServiceRequest().getSubService();
        if (providerServices.contains(subService)) {
            saveNewServiceOffer(serviceOffer);
            serviceOffer.getServiceRequest().setStatus(OrderStatus.UNDER_SELECTION);
        } else {
            throw new RuntimeException("this request service is not in your selection.");
        }
    }
}
