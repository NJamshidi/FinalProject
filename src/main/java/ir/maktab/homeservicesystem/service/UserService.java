package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.users.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class UserService {
    private UserDao userDao;

    public void saveNewUser(User user) {

        Optional<User> foundUser = userDao.findUserByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new RuntimeException("this email exist!");
        } else {
            userDao.save(user);
            System.out.println(user.getEmail() + " saved");
        }
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User findUserByUserNameAndPass(String username, String password) {
        Optional<User> user = userDao.findUserByUserNameAndPass(username, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("user not exist!");
        }
    }

    public User findByEmail(String email) {
        Optional<User> user = userDao.findUserByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("user not exist!");
        }
    }
}
