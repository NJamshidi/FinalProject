package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.UserFeedbackDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.userFeedback.UserFeedbackCreateEntity;
import ir.maktab.homeservicesystem.dto.userFeedback.UserFeedbackCreateResult;
import ir.maktab.homeservicesystem.exception.FeedbackException;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFeedbackService {
    private final UserFeedbackDao userFeedbackDao;
    private final CustomerService customerService;

    @Transactional
    public UserFeedbackCreateResult save(UserFeedbackCreateEntity userFeedbackCreateEntity) {
        Customer customer = customerService.findCustomerById(userFeedbackCreateEntity.getCustomerId());

        Order order = customer.getOrders().stream()
                .filter(o -> o.getId() == userFeedbackCreateEntity.getOrderId())
                .findFirst()
                .orElseThrow(() -> new NotFoundObjectException("order ", userFeedbackCreateEntity.getOrderId()));

        if (order.getStatus() != OrderStatus.DONE) {
            throw new FeedbackException("Feedback are only sent for orders with done status");
        }

        Offer acceptOffer = order.getAcceptedOffer();
        Expert expert = acceptOffer.getExpert();

        UserFeedback userFeedback = userFeedbackCreateEntity.toEntity(expert,customer,acceptOffer);
        expert.addUserFeedback(userFeedback);
        UserFeedback saveUserFeedbackResult = userFeedbackDao.save(userFeedback);
        return new UserFeedbackCreateResult(saveUserFeedbackResult.getId());
    }
}
