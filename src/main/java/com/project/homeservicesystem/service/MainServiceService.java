package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.MainServiceDao;
import com.project.homeservicesystem.entities.services.MainService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class MainServiceService {
    private MainServiceDao mainServiceDao;
    public void saveNewService(MainService mainService) {
        mainServiceDao.save(mainService);
    }

    public MainService findServiceByTitle(String title) {
        return mainServiceDao.findByTitle(title);
    }
}
