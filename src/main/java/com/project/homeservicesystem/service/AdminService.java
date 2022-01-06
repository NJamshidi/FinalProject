package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.AdminDao;
import com.project.homeservicesystem.entities.users.Admin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class AdminService {
    private AdminDao adminDao;

    public void saveNewAdmin(Admin admin) {
        adminDao.save(admin);
    }

    public void updateAdmin(Admin admin) {
        adminDao.update(admin);
    }

    public void deleteAdmin(Admin admin) {
        adminDao.delete(admin);
    }

    public List<Admin> findAll() {
        return adminDao.findAll();
    }

    public Admin findAminByUseAndPass(String username, String password) {
        Optional<Admin> admin = adminDao.findByUserNameAndPass(username, password);
        if (admin.isPresent()) {
            return admin.get();
        } else
            throw new RuntimeException("admin not exist!");
    }

    public boolean isExist(String username, String password) {
        Optional<Admin> found = adminDao.findByUserNameAndPass(username, password);
        if (found != null)
            return true;
        else return false;
    }
}
