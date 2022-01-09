package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class MainServiceService {
    private MainServiceDao mainServiceDao;

    public void saveNewService(MainService mainService) {

        Optional<MainService> foundMainService = mainServiceDao.findByTitle(mainService.getTitle());
        if (foundMainService.isPresent()) {
            throw new RuntimeException("service exist");
        } else {
            mainServiceDao.save(mainService);
        }
    }

    public MainService findServiceByTitle(String title) {

        Optional<MainService> main = mainServiceDao.findByTitle(title);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new RuntimeException("mainservice not exist");
    }


    public void deleteMainService(MainService mainService) {
        Optional<MainService> foundMainService = mainServiceDao.findByTitle(mainService.getTitle());
        if (foundMainService.isPresent()) {
            mainServiceDao.delete(mainService);
        } else {
            throw new RuntimeException("mainservice not exist");
        }
    }

    public void updateMainService(MainService mainService) {
        mainServiceDao.update(mainService);
    }

    public List<MainService> findAllMainService() {
        List<MainService> all = mainServiceDao.findAll();
        if (all.size() != 0) {
            return all;
        } else
            throw new RuntimeException("no mainService Exist");
    }
    public MainService findServiceById(Integer id) {

        Optional<MainService> main = mainServiceDao.findById(id);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new RuntimeException("mainservice not exist");
    }
}