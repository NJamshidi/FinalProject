package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.SubServiceDao;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

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
}
