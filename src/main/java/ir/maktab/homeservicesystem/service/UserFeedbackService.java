package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.UserFeedbackDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.mapper.UserFeedbackMapper;
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
    private UserFeedbackMapper userFeedbackMapper;

    @Transactional
    public UserFeedbackDto save(UserFeedbackDto userFeedbackDto) {
        Customer customer = customerService.loadById(userFeedbackDto.getId());

        Order order = customer.getOrders().stream()
                .filter(o -> o.getId() == userFeedbackDto.getId())
                .findFirst()
                .orElseThrow(() -> new NotFoundObjectException("order ", userFeedbackDto.getId()));

        if (order.getStatus() != OrderStatus.DONE) {
            throw new FeedbackException("Feedback are only sent for orders with done status");
        }

        Offer acceptOffer = order.getAcceptedOffer();
        Expert expert = acceptOffer.getExpert();

        UserFeedback userFeedback = userFeedbackMapper.toEntity(userFeedbackDto);
        expert.addUserFeedback(userFeedback);
        UserFeedback saveUserFeedbackResult = userFeedbackDao.save(userFeedback);
        return userFeedbackMapper.toDto(saveUserFeedbackResult);
    }
}
