package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.SubServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.dto.service.subService.AddExpertToSubService;
import ir.maktab.homeservicesystem.dto.service.ServiceCreateResult;
import ir.maktab.homeservicesystem.dto.service.subService.RemoveExpertFromSubService;
import ir.maktab.homeservicesystem.dto.service.subService.SubServiceCreateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceDao subServiceDao;
    private final ExpertService expertService;
    private final MainServiceService mainServiceService;

    public SubService findSubServiceById(int id) {
        return subServiceDao.getById(id);
    }

    public List<SubService> findSubServicesByMainServiceId(int id) {
        return subServiceDao.findByMainServiceId(id);
    }

    @Transactional
    public AddExpertToSubService addExpert(int subServiceId, int expertId) {
        SubService subService = subServiceDao.getById(subServiceId);
        Expert expert = expertService.findExpertById(expertId);
        subService.addExpert(expert);
        SubService result = subServiceDao.save(subService);
        return AddExpertToSubService.builder()
                .expertId(expertId)
                .subServiceId(result.getId())
                .successFull(true)
                .build();
    }

    @Transactional
    public RemoveExpertFromSubService removeExpert(int subServiceId, int expertId) {
        SubService subService = subServiceDao.getById(subServiceId);
        Expert expert = expertService.findExpertById(expertId);
        subService.removeExpert(expert);
        expert.removeSubService(subService);
        SubService subServiceResult = subServiceDao.save(subService);
        return RemoveExpertFromSubService.builder()
                .expertId(expertId)
                .subServiceId(subServiceResult.getId())
                .successFull(true)
                .build();
    }

    @Transactional
    public ServiceCreateResult saveSubService(SubServiceCreateEntity subServiceCreateEntity) {
        SubService subService = new SubService();
        subService.setName(subServiceCreateEntity.getName());

        MainService mainService = mainServiceService.findMainServiceById(subServiceCreateEntity.getMainServiceId());
        subService.setMainService(mainService);
        mainService.addSubService(subService);

        SubService saveSubServiceResult =subServiceDao.save(subService);
        return  new ServiceCreateResult(saveSubServiceResult.getId());
    }

}
