/*
package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ExpertServiceTest {
    @Autowired
    private ExpertService expertService;

    @Autowired
    private SubServiceService subServiceService;

    @Test
    void save() {
        Expert expert = new Expert();
        expert.setFirstName("expertAhmad");
        expert.setLastName("expertAhmadi");
        expert.setEmail("expertAhmad@gmail.com");
        expert.setPassword("12345678asd");
        expert.setExpertStatus(UserStatus.NEW);

        SubService subService1 = subServiceService.findById(1);
        SubService subService2 = subServiceService.findById(2);

        Set<SubService> subServiceSet = new HashSet<>();
        subServiceSet.add(subService1);
        subServiceSet.add(subService2);
        expert.setSubService(subServiceSet);
        Expert saveExResult = expertService.save(expert);
        assertNotNull(saveExResult);
    }
    @Test
    void loadByEmail() {
        Expert expert = expertService.findExpertByEmail("expertAhmad@gmail.com");
        assertEquals(1, expert.getId());
    }
    @Test
    void changePass() {
        Expert expert =expertService.changePassword(1, "12345678asd", "789456asd78");
        assertEquals("789456asd78", expert.getPassword());
    }

    @Test
    void changePasswordWithIncorrectNewPass() {
        assertThrows(IncorrectInformationException.class, () ->
                expertService.changePassword(1, "456asd78", "123")
        );
    }
    @Test
    void changePassWithIncorrectOldPass() {
        assertThrows(IncorrectInformationException.class, () ->
                expertService.changePassword(1, "123asd45", "456asd78")
        );
    }


}
*/
