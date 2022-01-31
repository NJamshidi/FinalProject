package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.ExpertDao;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordEntity;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordResult;
import ir.maktab.homeservicesystem.dto.user.expert.*;
import ir.maktab.homeservicesystem.exception.DuplicateInformationException;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertDao expertDao;
    private Validation validation = new Validation();

//    @Autowired
//    public ExpertService(ExpertDao expertDao) {
//        this.expertDao = expertDao;
//    }
//    @PostConstruct
//    public void init() {
//        setJpaRepository(expertDao);
//    }


    public ExpertCreateDto saveExpert(ExpertCreateEntity expertCreateEntity) throws IOException {
        Expert expert = expertCreateEntity.toEntity();
        Expert foundedByEmail = findExpertByEmail(expert.getEmail());
        if (foundedByEmail != null) {
            throw new DuplicateInformationException("this email used with another expert");
        }

        if (validation.validPassword(expert.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setExpertStatus(UserStatus.NEW);
        expert.setUserRole(UserRole.EXPERT);
        expert.setCredit(0.0);
        Expert expertSave = expertDao.save(expert);
        return new ExpertCreateDto().toDto(expertSave);
    }

    public Expert update(Expert expert) {
        Expert foundedByEmail = findExpertByEmail(expert.getEmail());
        if (foundedByEmail != null && !Objects.equals(foundedByEmail.getId(), expert.getId())) {
            throw new DuplicateInformationException("this email used with another expert");
        }

        if (validation.validPassword(expert.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setExpertStatus(UserStatus.UNDER_APPROVAL);
        return update(expert);
    }

    public ExpertUpdateResult updateExpert(ExpertUpdateEntity expertUpdateEntity) throws IOException {
        Expert expert = expertUpdateEntity.toEntity();
        Expert foundedExpert = expertDao.getById(expert.getId());
        if (!foundedExpert.getPassword().equals(expert.getPassword())) {
            throw new IncorrectInformationException("Password is not correct!");
        }
        Expert findedByEmail = findExpertByEmail(expert.getEmail());
        if (findedByEmail != null && !Objects.equals(findedByEmail.getId(), expert.getId())) {
            throw new DuplicateInformationException("Another expert with this email exists");
        }
        expert.setUserRole(UserRole.EXPERT);
        expert.setExpertStatus(UserStatus.UNDER_APPROVAL);
        Expert expertUpdate = expertDao.save(expert);
        return ExpertUpdateResult.builder()
                .id(expertUpdate.getId())
                .successFull(true)
                .build();
    }

    public Expert findExpertByEmail(String email) {

        return expertDao.findByEmail(email);
    }

    public Expert findExpertById(int expertId) {
        return expertDao.getById(expertId);
    }

    @Transactional
    public UserChangePasswordResult changePassword(UserChangePasswordEntity changePasswordEntity) {
        int expertId = changePasswordEntity.getUserId();
        String oldPass = changePasswordEntity.getCurrentPassword();
        String newPass = changePasswordEntity.getNewPassword();
        String confirmNewPass = changePasswordEntity.getNewPasswordConfirm();

        if (!newPass.equals(confirmNewPass)) {
            throw new IncorrectInformationException("New password and confirm password doesn't match");
        }
        Expert expert = expertDao.getById(expertId);
        if (!Objects.equals(expert.getPassword(), oldPass)) {
            throw new IncorrectInformationException("Old password doesn't match");
        }
        if (validation.validPassword(newPass)) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setPassword(newPass);
        Expert resultChangePAss = expertDao.save(expert);
        return new UserChangePasswordResult(resultChangePAss.getId(), resultChangePAss.getPassword());
    }

    public ExpertList findAllExperts() {
        List<Expert> expertList = expertDao.findAll();
        ExpertList expertListResult = new ExpertList();
        expertList.forEach((p) -> expertListResult.addExpertDto(new ExpertCreateDto().toDto(p)));
        return expertListResult;
    }

    public ExpertList findAllExpertsByStatus(UserStatus status) {
        List<Expert> expertList = expertDao.findAllByExpertStatus(status);
        ExpertList expertListResult = new ExpertList();
        expertList.forEach((p) -> expertListResult.addExpertDto(new ExpertCreateDto().toDto(p)));
        return expertListResult;
    }

    public ExpertList findExpertsBySubServiceId(int subServiceId) {
        List<Expert> expertList = expertDao.findAllBySubServiceId(subServiceId);
        ExpertList expertListResult = new ExpertList();
        expertList.forEach((p) -> expertListResult.addExpertDto(new ExpertCreateDto().toDto(p)));
        return expertListResult;
    }
}