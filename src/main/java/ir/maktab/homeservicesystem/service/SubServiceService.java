package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.SubServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class SubServiceService extends BaseService<SubService, Integer> {
    private final SubServiceDao subServiceDao;
    private final ExpertService expertService;
    @Autowired
    public SubServiceService(SubServiceDao subServiceDao, ExpertService expertService) {
        this.subServiceDao =subServiceDao;
        this.expertService = expertService;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(subServiceDao);
    }

    public List<SubService> findByMainServiceId(int id) {
        return subServiceDao.findByMainServiceId(id);
    }
    @Transactional
    public SubService addExpert(int subServiceId, int expertId) {
        SubService subService = subServiceDao.getById(subServiceId);
        Expert expert = expertService.findById(expertId);

        Set<Expert> experts = subService.getExpert();
        experts.add(expert);
        subService.setExpert(experts);

        Set<SubService> subServices = expert.getSubService();
        subServices.add(subService);
        expert.setSubService(subServices);

        expertService.update(expert);
        return super.update(subService);
    }
    @Transactional
    public SubService removeExpert(int subServiceId, int expertId) {
        SubService subService = subServiceDao.getById(subServiceId);
        Expert expert = expertService.findById(expertId);

        Set<Expert> experts = subService.getExpert();
        experts.remove(expert);
        subService.setExpert(experts);

        Set<SubService> subServices = expert.getSubService();
        subServices.remove(subService);
        expert.setSubService(subServices);

        expertService.update(expert);
        return super.update(subService);
    }

}
