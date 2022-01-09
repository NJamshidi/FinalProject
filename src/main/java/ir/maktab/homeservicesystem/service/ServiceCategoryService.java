package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.SubServiceDao;
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
    private SubServiceDao subServiceDao;

    public void saveServiceCategory(SubService subService) {
        Optional<SubService> foundServiceCategory = subServiceDao.findByName(subService.getName());
        if (subService.getMainService() == null) {
            throw new RuntimeException("category can be empty");
        }
        if (foundServiceCategory.isPresent()) {
            throw new RuntimeException("category exist");
        } else {
            subServiceDao.save(subService);
        }
    }

    public void updateServiceCategory(SubService subService) {
        subServiceDao.update(subService);
    }

    public void deleteServiceCategory(SubService subService) {
        Optional<SubService> foundServiceCategory = subServiceDao.findByName(subService.getName());
        if (foundServiceCategory.isPresent()) {
            subServiceDao.delete(subService);
        } else {
            throw new RuntimeException("category not exist");
        }
    }

    public List<SubService> findAll() {
        return subServiceDao.findAll();
    }


    public SubService findById(int id) {
        Optional<SubService> foundServiceCategory = subServiceDao.findById(id);
        if (foundServiceCategory.isPresent()) {
            return foundServiceCategory.get();
        } else {
            throw new RuntimeException("category not exist");
        }
    }

    public SubService findByName(String name) {
        Optional<SubService> foundServiceCategory2 = subServiceDao.findByName(name);
        if (foundServiceCategory2.isPresent()) {
            return foundServiceCategory2.get();
        } else {
            throw new RuntimeException("category not exist");
        }
    }
}
