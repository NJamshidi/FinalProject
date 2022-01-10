package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.UserFeedbackDao;
import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
//@RequiredArgsConstructor
public class UserFeedbackService extends BaseService<UserFeedback, Integer> {
    private final UserFeedbackDao userFeedbackDao;

    @Autowired
    public UserFeedbackService(UserFeedbackDao userFeedbackDao) {
        this.userFeedbackDao =userFeedbackDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(userFeedbackDao);
    }
}
