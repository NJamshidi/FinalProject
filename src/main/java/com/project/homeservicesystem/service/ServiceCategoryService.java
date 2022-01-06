package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ServiceCategoryDao;
import com.project.homeservicesystem.entities.services.ServiceCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ServiceCategoryService {
    private ServiceCategoryDao serviceCategoryDao;

    public void saveServiceCategory(ServiceCategory serviceCategory) {
        Optional<ServiceCategory> foundServiceCategory = serviceCategoryDao.findByName(serviceCategory.getName());
        if (serviceCategory.getMainService() == null) {
            throw new RuntimeException("category can be empty");
        }
        if (foundServiceCategory.isPresent()) {
            throw new RuntimeException("category exist");
        } else {
            serviceCategoryDao.save(serviceCategory);
        }
    }

    public void updateServiceCategory(ServiceCategory serviceCategory) {
        serviceCategoryDao.update(serviceCategory);
    }

    public void deleteServiceCategory(ServiceCategory serviceCategory) {
        Optional<ServiceCategory> foundServiceCategory = serviceCategoryDao.findByName(serviceCategory.getName());
        if (foundServiceCategory.isPresent()) {
            serviceCategoryDao.delete(serviceCategory);
        } else {
            throw new RuntimeException("category not exist");
        }
    }

    public List<ServiceCategory> findAll() {
        return serviceCategoryDao.findAll();
    }


    public ServiceCategory findById(int id) {
        Optional<ServiceCategory> foundServiceCategory = serviceCategoryDao.findById(id);
        if (foundServiceCategory.isPresent()) {
            return foundServiceCategory.get();
        } else {
            throw new RuntimeException("category not exist");
        }
    }

    public ServiceCategory findByName(String name) {
        Optional<ServiceCategory> foundServiceCategory2 = serviceCategoryDao.findByName(name);
        if (foundServiceCategory2.isPresent()) {
            return foundServiceCategory2.get();
        } else {
            throw new RuntimeException("category not exist");
        }
    }
}
