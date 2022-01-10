package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.AdminDao;
import ir.maktab.homeservicesystem.data.entities.users.Admin;
import ir.maktab.homeservicesystem.exception.DuplicateInformationException;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
//@RequiredArgsConstructor
public class AdminService extends BaseService<Admin, Integer> {
    private final AdminDao adminDao;
    Validation validation = new Validation();
    @Autowired
    public AdminService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(adminDao);
    }

    @Override
    public Admin save(Admin admin) {
        Admin foundedByemail = findAdminByEmail(admin.getEmail());
        if (foundedByemail != null) {
            throw new DuplicateInformationException("this email used with another admin");
        }

        if (Validation.validPassword(admin.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        return super.save(admin);
    }

    @Override
    public Admin update(Admin admin) {
        Admin foundedByemail = findAdminByEmail(admin.getEmail());
        if (foundedByemail != null && !Objects.equals(foundedByemail.getId(), admin.getId())) {
            throw new DuplicateInformationException("this email used with another admin");
        }

        if (validation.validPassword(admin.getPassword())) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        return super.update(admin);
    }

    public Admin findAdminByEmail(String email) {
        return adminDao.findByEmail(email);
    }

    @Transactional
    public Admin changeAdminPassword(int adminId, String oldPassword, String newPassword) {
        Admin admin = adminDao.getById(adminId);
        if (!Objects.equals(admin.getPassword(), oldPassword)) {
            throw new IncorrectInformationException("Old password is incorrect");
        }
        if (validation.validPassword(newPassword)) {
            throw new IncorrectInformationException("Password length must be at least 8 character and contain letters and numbers");
        }
        admin.setPassword(newPassword);
        return super.update(admin);
    }

//    @Transactional
//    public void UpdatePassword(String newPassword, int id) {
//        adminDao.UpdatePassword(newPassword, id);
//    }
}
