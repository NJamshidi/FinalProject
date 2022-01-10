package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.CustomerDao;
import ir.maktab.homeservicesystem.data.dao.ExpertDao;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.validation.Validation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ExpertService extends BaseService<Expert, Integer> {
    private final ExpertDao expertDao;
    private Validation validation = new Validation();
    @Autowired
    public ExpertService(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(expertDao);
    }

    @Override
    public Expert save(Expert expert) {
        Expert foundedByEmail = findExpertByEmail(expert.getEmail());
        if (foundedByEmail != null) {
            throw new DuplicateInformationException("this email used with another expert");
        }

        if (validation.validPassword(expert.getPassword())) {
            throw new  IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setExpertStatus(UserStatus.NEW);
        return super.save(expert);
    }

    @Override
    public Expert update(Expert expert) {
        Expert foundedByEmail = findExpertByEmail(expert.getEmail());
        if (foundedByEmail != null && !Objects.equals(foundedByEmail.getId(), expert.getId())) {
            throw new DuplicateInformationException("this email used with another expert");
        }

        if (validation.validPassword(expert.getPassword()))  {
            throw new  IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setExpertStatus(UserStatus.UNDER_APPROVAL);
        return super.update(expert);
    }

    public Expert findExpertByEmail(String email) {
        return expertDao.findByEmail(email);
    }

    @Transactional
    public Expert changePassword(int expertId, String oldPassword, String newPassword) {
        Expert expert = expertDao.getById(expertId);
        if (!Objects.equals(expert.getPassword(), oldPassword)) {
            throw new IncorrectInformationException("Old password is incorrect");
        }
        if (validation.validPassword(newPassword)) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setPassword(newPassword);
        return super.update(expert);
    }
    @Transactional
    public Expert increaseCredit(int id, Double amount) {
        Expert expert = findById(id);
        double newCredit = expert.getCredit() + amount;
        expert.setCredit(newCredit);
        return super.update(expert);
    }
    @Transactional
    public Expert decreaseCredit(int id, Double amount) {
        Expert expert = findById(id);
        double credit = expert.getCredit();
        if (credit < amount) {
            throw new NotEnoughException("Credit is not enough");
        }
        double newCredit = credit - amount;
        expert.setCredit(newCredit);
        return super.update(expert);
    }


}