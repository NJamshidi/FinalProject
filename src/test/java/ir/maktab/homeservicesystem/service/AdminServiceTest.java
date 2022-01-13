package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.users.Admin;
import ir.maktab.homeservicesystem.exception.DuplicateInformationException;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    @Order(1)

    void save() {
        Admin admin = new Admin();
        admin.setFirstName("narges5");
        admin.setLastName("jam5");
        admin.setEmail("narges5@mail.com");
        admin.setUserName("admin5");
        admin.setPassword("123asd45");
        Admin saveAdminResult = adminService.save(admin);
        assertNotNull(saveAdminResult);
    }
    @Test
    @Order(4)

    void saveWithPassJustAlphabet() {
        Admin admin = new Admin();
        admin.setFirstName("ali");
        admin.setLastName("alavi");
        admin.setUserName("admin2");
        admin.setEmail("ali@mail.com");
        admin.setPassword("aaaaaaaaa");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }
    @Test
    @Order(3)

    void saveWithPassJustNumber() {
        Admin admin = new Admin();
        admin.setFirstName("ahmad");
        admin.setLastName("ahmadi");
        admin.setUserName("admin3");
        admin.setEmail("ahmad@mail.com");
        admin.setPassword("12312345");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }
    @Test
    @Order(5)

    void SaveWithPassLess8Char() {
        Admin admin = new Admin();
        admin.setFirstName("narges1");
        admin.setLastName("jamshidi1");
        admin.setUserName("admin7");
        admin.setEmail("narges1@mail.com");
        admin.setPassword("1223a");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }
    @Test
    @Order(2)

    void SaveWithDuplicateEmail() {
        Admin admin = new Admin();
        admin.setFirstName("narges1");
        admin.setLastName("jam1");
        admin.setUserName("admin1");
        admin.setEmail("narges1@mail.com");
        admin.setPassword("123asd45");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }

    @Test
    @Order(6)

    void changePass() {
        Admin admin = adminService.changeAdminPassword(1, "123asd45", "456asd78");
        assertEquals("456asd78", admin.getPassword());
    }

    @Test
    @Order(7)

    void ChangePassWithIncorrectOldPass() {
        assertThrows(IncorrectInformationException.class, () ->
                adminService.changeAdminPassword(1, "123asd45", "456asd78")
        );
    }

    @Test
    @Order(9)

    void changePassWithInvalidNewPass() {
        assertThrows(IncorrectInformationException.class, () ->
                adminService.changeAdminPassword(1, "456asd78", "123")
        );
    }


    @Test
    @Order(11)

    void update() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setFirstName("nima");
        admin.setLastName("jam");
        admin.setEmail("narges@mail.com");
        admin.setUserName("admin1");
        admin.setPassword("123asd45");
        Admin updateAdminResult = adminService.update(admin);
        assertEquals("nima", updateAdminResult.getFirstName());
    }

    @Test
    @Order(12)

    void updateWithDuplicateEmail() {
        Admin admin = new Admin();
        admin.setId(3);
        admin.setFirstName("reza");
        admin.setLastName("rezaie");
        admin.setEmail("ahmad@mail.com");
        admin.setPassword("123abd47");
        assertThrows(DuplicateInformationException.class, () -> adminService.update(admin));
    }
    @Test
    @Order(10)

    void loadByEmail() {
        String email = "narges@mail.com";
        Admin loadResult = adminService.findAdminByEmail(email);
        assertEquals("narges@mail.com",loadResult.getEmail());
    }
    @Test
    @Order(13)

    void loadByIdExist() {
        Admin loadByIdResult = adminService.findById(1);
        assertNotNull(loadByIdResult);
    }
    @Order(14)

    @Test
    void loadByIdNotExist() {
        assertThrows(NotFoundObjectException.class, () -> adminService.findById(20));
    }

    @Test
    @Order(15)

    void findAll() {
        List<Admin> adminList = adminService.findAll();
        assertEquals(3, adminList.size());
    }

    @Test
    @Order(16)

    void delete() {
        adminService.removeById(2);

        List<Admin> adminList = adminService.findAll();
        assertEquals(2, adminList.size());
    }

    @Test
    @Order(17)

    void deleteNotExist() {
        assertThrows(EmptyResultDataAccessException.class, () -> adminService.removeById(20));
    }
}