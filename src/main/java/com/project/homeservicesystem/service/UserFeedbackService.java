package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.UserFeedbackDao;
import com.project.homeservicesystem.entities.users.UserFeedback;
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
