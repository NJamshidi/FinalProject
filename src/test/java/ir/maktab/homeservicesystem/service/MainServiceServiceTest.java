package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class MainServiceServiceTest {
    @Autowired
    private MainServiceService mainServiceService;

    @Test
    void save(){
        MainService mainService = new MainService();
        mainService.setName("MainServiceA");

        MainService mainServiceResult = mainServiceService.save(mainService);
        assertNotNull(mainServiceResult);
    }

    @Test
    void loadById(){
        MainService mainService  = mainServiceService.findById(1);
        mainService.getSubService().forEach(System.out::println);
        assertNotNull(mainService);
    }
}
