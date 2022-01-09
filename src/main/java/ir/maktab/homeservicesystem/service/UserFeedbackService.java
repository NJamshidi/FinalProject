package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.UserFeedbackDao;
import ir.maktab.homeservicesystem.data.entities.users.UserFeedback;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class UserFeedbackService {
    private UserFeedbackDao userFeedbackDao;
    public void save(UserFeedback userFeedback){userFeedbackDao.save(userFeedback);}
}
