package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubServiceServiceTest {
    @Autowired
    private SubServiceService subServiceService;
    @Autowired
    private MainServiceService mainServiceService;
    @Autowired
    private ExpertService expertService;

    @Test
    void save() {
        MainService mainService = mainServiceService.findById(2);
        System.out.println(mainService.getName());
        SubService subService = new SubService();
        subService.setName("SubServiceB");
        subService.setMainService(mainService);
        SubService saveSubServiceResult = subServiceService.save(subService);
        assertNotNull(saveSubServiceResult);
    }

    @Test
    void addExpert() {
        SubService subService = subServiceService.addExpert(2, 3);
        assertTrue(subService.getExpert().contains(expertService.findById(3)));
    }

    @Test
    void deleteExpert() {
        SubService subService = subServiceService.removeExpert(2, 3);
        assertFalse(subService.getExpert().contains(expertService.findById(3)));
    }

    @Test
    void findByMainServiceId() {
        List<SubService> subServices = subServiceService.findByMainServiceId(1);
        subServices.forEach(System.out::println);
        assertEquals(4, subServices.size());
    }
}
