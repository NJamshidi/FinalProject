package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceOfferDao;
import com.project.homeservicesystem.dao.UserDao;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.entities.users.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class UserService {
    private UserDao userDao;
    public void saveNewUser(User user) {
        userDao.save(user);
    }
    public User findUserByUserNameAndPass(String username, String password) {
        return userDao.findUserByUserNameAndPass(username, password);
    }
}
