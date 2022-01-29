package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.MainServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.MainServiceList;
import ir.maktab.homeservicesystem.dto.service.ServiceCreateResult;
import ir.maktab.homeservicesystem.dto.service.ServiceUpdateResult;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateDto;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateEntity;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceUpdateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MainServiceService {
    private final MainServiceDao mainServiceDao;

    @Transactional(readOnly = true)
    public MainService findMainServiceById(int id) {
        return mainServiceDao.getById(id);
    }

    @Transactional(readOnly = true)
    public MainServiceCreateDto findMainServiceByIdReturnDto(int id) {
        MainService mainService = mainServiceDao.getById(id);
        return new MainServiceCreateDto().toDto(mainService);

    }

    @Transactional
    public ServiceCreateResult saveMainService(MainServiceCreateEntity mainServiceCreateEntity) {
        MainService mainService = new MainService();
        mainService.setName(mainServiceCreateEntity.getName());
        MainService saveMainService = mainServiceDao.save(mainService);
        return new ServiceCreateResult(saveMainService.getId());
    }

    @Transactional
    public ServiceUpdateResult updateMainService(MainServiceUpdateEntity mainServiceUpdateEntity) {
        MainService mainService = mainServiceDao.getById(mainServiceUpdateEntity.getId());
        mainService.setName(mainServiceUpdateEntity.getName());
        MainService updateMainSevice = mainServiceDao.save(mainService);
        return new ServiceUpdateResult(updateMainSevice.getId(),true);
    }


    @Transactional(readOnly = true)
    public MainServiceList findAllMainServices() {
        List<MainService> mainServices = mainServiceDao.findAll();
        MainServiceList mainServiceList = new MainServiceList();
        mainServices.forEach((c) -> mainServiceList.addMainServiceDto(new MainServiceCreateDto().toDto(c)));
        return mainServiceList;
    }
}
