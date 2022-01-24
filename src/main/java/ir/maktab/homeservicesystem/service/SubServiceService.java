package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.SubServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.dto.SubServiceDto;
import ir.maktab.homeservicesystem.dto.mapper.SubServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceDao subServiceDao;
    private final ExpertService expertService;
    private SubServiceMapper subServiceMapper;
    private final MainServiceService mainServiceService;

    public SubService loadById(int id) {
        return subServiceDao.getById(id);
    }

    public List<SubService> findByMainServiceId(int id) {
        return subServiceDao.findByMainServiceId(id);
    }

    @Transactional
    public SubServiceDto addExpert(int subServiceId, int expertId) {
        SubService subService = subServiceDao.getById(subServiceId);
        Expert expert = expertService.findById(expertId);
        subService.addExpert(expert);
        SubService subServiceresult = subServiceDao.save(subService);
        return subServiceMapper.toDto(subServiceresult);
    }

    @Transactional
    public SubServiceDto removeExpert(int subServiceId, int expertId) {
        SubService subService = subServiceDao.getById(subServiceId);
        Expert expert = expertService.findById(expertId);
        subService.removeExpert(expert);
        expert.removeSubService(subService);
        SubService subServiceResult = subServiceDao.save(subService);
        return subServiceMapper.toDto(subServiceResult);
    }

    @Transactional
    public SubServiceDto saveSubService(SubServiceDto subserviceDto) {
        SubService subService = new SubService();
        subService.setName(subserviceDto.getName());

        MainService mainService = mainServiceService.loadById(subserviceDto.getMainService().getId());
        subService.setMainService(mainService);
        mainService.addSubService(subService);

        SubService saveSubServiceResult =subServiceDao.save(subService);
        return subServiceMapper.toDto(saveSubServiceResult);
    }
}
