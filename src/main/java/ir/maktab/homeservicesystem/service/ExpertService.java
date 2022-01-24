package ir.maktab.homeservicesystem.service;


import ir.maktab.homeservicesystem.data.dao.ExpertDao;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.entities.users.ExpertList;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.dto.ExpertDto;
import ir.maktab.homeservicesystem.dto.mapper.ExpertMapper;
import ir.maktab.homeservicesystem.dto.mapper.UserChangePasswordParam;
import ir.maktab.homeservicesystem.dto.mapper.UserChangePasswordResult;
import ir.maktab.homeservicesystem.exception.DuplicateInformationException;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
//@RequiredArgsConstructor
public class ExpertService {
    private final ExpertDao expertDao;
    private ExpertMapper expertMapper;
    private Validation validation = new Validation();

    @Autowired
    public ExpertService(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }
//    @PostConstruct
//    public void init() {
//        setJpaRepository(expertDao);
//    }


    public ExpertDto save(ExpertDto expertDto) {
        Expert expert = expertMapper.toEntity(expertDto);
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
        ExpertDto expertSaveDto = expertMapper.toDto(expertSave);
        return expertSaveDto;
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

    public ExpertDto updateExpert(ExpertDto expertDto) {
        Expert expert = expertMapper.toEntity(expertDto);
        Expert foundedByEmail = findExpertByEmail(expert.getEmail());
        if (foundedByEmail != null && !Objects.equals(foundedByEmail.getId(), expert.getId())) {
            throw new DuplicateInformationException("this email used with another expert");
        }

        if (validation.validPassword(expert.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        expert.setUserRole(UserRole.EXPERT);
        expert.setExpertStatus(UserStatus.UNDER_APPROVAL);
        Expert expertUpdate = expertDao.save(expert);
        return expertMapper.toDto(expertUpdate);
    }

    public Expert findExpertByEmail(String email) {

        return expertDao.findByEmail(email);
    }

    public Expert findById(int expertId) {
        return expertDao.getById(expertId);
    }

    @Transactional
    public UserChangePasswordResult changePassword(UserChangePasswordParam changePasswordParam) {
    int expertId = changePasswordParam.getUserId();
    String oldPass = changePasswordParam.getCurrentPassword();
    String newPass = changePasswordParam.getNewPassword();
    String confirmNewPass = changePasswordParam.getNewPasswordConfirm();

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
    Expert result = expertDao.save(expert);
        return new UserChangePasswordResult(result.getId(), result.getPassword());
}

    public ExpertList loadAllExperts() {
        List<Expert> expertList = expertDao.findAll();
        ExpertList expertListResult = new ExpertList();
        expertList.forEach((p) -> expertListResult.addExpertDto(expertMapper.toDto(p)));

        return expertListResult;
    }

    public ExpertList loadAllByStatus(UserStatus status) {
        List<Expert> expertList = expertDao.findAllByStatus(status);
        ExpertList expertListResult = new ExpertList();
        expertList.forEach((p) -> expertListResult.addExpertDto(expertMapper.toDto(p)));
        return expertListResult;
    }

    public ExpertList loadBySubServiceId(int subServiceId) {
        List<Expert> expertList = expertDao.findAllBySubServiceId(subServiceId);
        ExpertList expertListResult = new ExpertList();
        expertList.forEach((p) -> expertListResult.addExpertDto(expertMapper.toDto(p)));
        return expertListResult;
    }
}