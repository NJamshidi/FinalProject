package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.MainServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.MainServiceList;
import ir.maktab.homeservicesystem.dto.MainServiceDto;
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
    public MainServiceDto loadByIdReturnDto(int id) {
        MainService mainService = mainServiceDao.getById(id);
        return mainServiceMapper.toDto(mainService);

    }

    @Transactional
    public MainServiceDto saveMainService(MainServiceDto mainServiceDto) {
        MainService mainService = new MainService();
        mainService.setName(mainServiceDto.getName());
        MainService saveResult = mainServiceDao.save(mainService);
        return mainServiceMapper.toDto(saveResult);
    }

    @Transactional
    public MainServiceDto updateMainService(MainServiceDto mainServiceDto) {
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
