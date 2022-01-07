import com.project.homeservicesystem.config.HibernateConfig;
import com.project.homeservicesystem.entities.services.MainService;
import com.project.homeservicesystem.service.MainServiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
    MainServiceService mainServiceService = context.getBean(MainServiceService.class);
    MainService mainService;
    @BeforeEach
    void init() {
        mainService=new MainService();
    }

    @Test
    void giveNoCustomer_deleteMethode_ThrowException() {
        mainService.setTitle("VEHICLES");
        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () ->
                mainServiceService.saveNewService(mainService));
        Assertions.assertEquals(" main service already exist",result.getMessage());
    }}