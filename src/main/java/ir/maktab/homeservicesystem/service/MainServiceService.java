package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.MainServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.MainServiceList;
import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateDto;
import ir.maktab.homeservicesystem.dto.mapper.MainServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MainServiceService {
    private final MainServiceDao mainServiceDao;
    private MainServiceMapper mainServiceMapper;

    @Transactional(readOnly = true)
    public MainService loadById(int id) {
        return mainServiceDao.getById(id);
    }

    @Transactional(readOnly = true)
    public MainServiceCreateDto loadByIdReturnDto(int id) {
        MainService mainService = mainServiceDao.getById(id);
        return mainServiceMapper.toDto(mainService);

    }

    @Transactional
    public MainServiceCreateDto saveMainService(MainServiceCreateDto mainServiceDto) {
        MainService mainService = new MainService();
        mainService.setName(mainServiceDto.getName());
        MainService saveResult = mainServiceDao.save(mainService);
        return mainServiceMapper.toDto(saveResult);
    }

    @Transactional
    public MainServiceCreateDto updateMainService(MainServiceCreateDto mainServiceDto) {
        MainService mainService = mainServiceDao.getById(mainServiceDto.getId());
        mainService.setName(mainServiceDto.getName());
        MainService saveResult = mainServiceDao.save(mainService);
        return mainServiceMapper.toDto(saveResult);
    }


    @Transactional(readOnly = true)
    public MainServiceList loadAll() {
        List<MainService> mainServices = mainServiceDao.findAll();
        MainServiceList mainServiceList = new MainServiceList();
        mainServices.forEach((c) -> mainServiceList.addMainServiceDto(mainServiceMapper.toDto(c)));
        return mainServiceList;
    }
}
