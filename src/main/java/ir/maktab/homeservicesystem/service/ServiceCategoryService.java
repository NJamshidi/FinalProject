package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.ServiceCategoryDao;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
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

    public void saveServiceCategory(SubService subService) {
        Optional<SubService> foundServiceCategory = serviceCategoryDao.findByName(subService.getName());
        if (subService.getMainService() == null) {
            throw new RuntimeException("category can be empty");
        }
        if (foundServiceCategory.isPresent()) {
            throw new RuntimeException("category exist");
        } else {
            serviceCategoryDao.save(subService);
        }
    }

    public void updateServiceCategory(SubService subService) {
        serviceCategoryDao.update(subService);
    }

    public void deleteServiceCategory(SubService subService) {
        Optional<SubService> foundServiceCategory = serviceCategoryDao.findByName(subService.getName());
        if (foundServiceCategory.isPresent()) {
            serviceCategoryDao.delete(subService);
        } else {
            throw new RuntimeException("category not exist");
        }
    }

    public List<SubService> findAll() {
        return serviceCategoryDao.findAll();
    }


    public SubService findById(int id) {
        Optional<SubService> foundServiceCategory = serviceCategoryDao.findById(id);
        if (foundServiceCategory.isPresent()) {
            return foundServiceCategory.get();
        } else {
            throw new RuntimeException("category not exist");
        }
    }

    public SubService findByName(String name) {
        Optional<SubService> foundServiceCategory2 = serviceCategoryDao.findByName(name);
        if (foundServiceCategory2.isPresent()) {
            return foundServiceCategory2.get();
        } else {
            throw new RuntimeException("category not exist");
        }
    }
}
