package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.OrderDao;
import ir.maktab.homeservicesystem.data.entities.services.ServiceOffer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.enumaration.OfferStatus;
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
public class ServiceRequestService {
    private OrderDao orderDao;

    public void saveNewServiceRequest(Order order) {
        orderDao.save(order);
    }

    public Order findById(Integer id) {return orderDao.findServiceRequestByID(id);}

    public ServiceOffer findAcceptOfferOfRequest(Order order) {
        ServiceOffer acceptOffer = null;
        if (order.getStatus().equals(OrderStatus.PAID)) {
            Set<ServiceOffer> serviceOffers = order.getOffers();
            for (ServiceOffer serviceOffer : serviceOffers) {
                if (serviceOffer.getServiceOfferStatus().equals(OfferStatus.ACCEPTED)) {
                    acceptOffer = serviceOffer;
                }
            }
            return acceptOffer;
        } else {
            throw new RuntimeException("not paid!");
        }
    }


}
