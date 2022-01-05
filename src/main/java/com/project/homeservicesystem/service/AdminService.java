package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.AdminDao;
import com.project.homeservicesystem.entities.users.Admin;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminService {
    private AdminDao adminDao;
    public void saveNewAdmin(Admin admin) {
        adminDao.save(admin);
    }

    public Admin findAminByUserNameAndPass(String userName, String password) {
        return adminDao.findByUserNameAndPass(userName,password);
    }
}
