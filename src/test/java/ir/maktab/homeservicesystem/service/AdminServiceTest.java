package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.users.Admin;
import ir.maktab.homeservicesystem.exception.DuplicateInformationException;
import ir.maktab.homeservicesystem.exception.IncorrectInformationException;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    void save() {
        Admin admin = new Admin();
        admin.setFirstName("narges");
        admin.setLastName("jam");
        admin.setEmail("narges@mail.com");
        admin.setPassword("123asd45");
        Admin saveAdminResult = adminService.save(admin);
        assertNotNull(saveAdminResult);
    }
    @Test
    void saveWithPassJustAlphabet() {
        Admin admin = new Admin();
        admin.setFirstName("ali");
        admin.setLastName("alavi");
        admin.setEmail("ali@mail.com");
        admin.setPassword("aaaaaaaaa");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }
    @Test
    void saveWithPassJustNumber() {
        Admin admin = new Admin();
        admin.setFirstName("ahmad");
        admin.setLastName("ahmadi");
        admin.setEmail("ahmad@mail.com");
        admin.setPassword("12312345");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }
    @Test
    void SaveWithPassLess8Char() {
        Admin admin = new Admin();
        admin.setFirstName("narges");
        admin.setLastName("jamshidi");
        admin.setEmail("narges@mail.com");
        admin.setPassword("123a");
        assertThrows(IncorrectInformationException.class, () -> adminService.save(admin));
    }
    @Test
    void SaveWithDuplicateEmail() {
        Admin admin = new Admin();
        admin.setFirstName("nahal");
        admin.setLastName("razavi");
        admin.setEmail("narges@mail.com");
        admin.setPassword("123qwe45");
        assertThrows(DuplicateInformationException.class, () -> adminService.save(admin));
    }

    @Test
    void changePass() {
        Admin admin = adminService.changeAdminPassword(1, "123asd45", "456asd78");
        assertEquals("456asd78", admin.getPassword());
    }

    @Test
    void ChangePassWithIncorrectOldPass() {
        assertThrows(IncorrectInformationException.class, () ->
                adminService.changeAdminPassword(1, "123asd45", "456asd78")
        );
    }

    @Test
    void changePassWithInvalidNewPass() {
        assertThrows(IncorrectInformationException.class, () ->
                adminService.changeAdminPassword(1, "456asd78", "123")
        );
    }


    @Test
    void update() {
        int id = 3;
        Admin admin = new Admin();
        admin.setId(id);
        admin.setFirstName("nima");
        admin.setLastName("nimaie");
        admin.setEmail("nima@mail.com");
        admin.setPassword("123asd45");
        Admin updateAdminResult = adminService.update(admin);
        assertEquals("nima", updateAdminResult.getFirstName());
    }

    @Test
    void updateWithDuplicateEmail() {
        int id = 3;
        Admin admin = new Admin();
        admin.setId(id);
        admin.setFirstName("reza");
        admin.setLastName("rezaie");
        admin.setEmail("ali@mail.com");
        admin.setPassword("123abd47");
        assertThrows(DuplicateInformationException.class, () -> adminService.update(admin));
    }
    @Test
    void loadByEmail() {
        String email = "narges@mail.com";
        Admin loadResult = adminService.findAdminByEmail(email);
        assertEquals("narges@mail.com",loadResult.getEmail());
    }
    @Test
    void loadByIdExist() {
        int id = 1;
        Admin loadByIdResult = adminService.findById(id);
        assertNotNull(loadByIdResult);
    }

    @Test
    void loadByIdNotExist() {
        int id = 20;
        assertThrows(NotFoundObjectException.class, () -> adminService.findById(id));
    }

    @Test
    void findAll() {
        List<Admin> adminList = adminService.findAll();
        assertEquals(3, adminList.size());
    }

    @Test
    void delete() {
        int id = 5;
        adminService.removeById(id);

        List<Admin> adminList = adminService.findAll();
        assertEquals(2, adminList.size());
    }

    @Test
    void deleteNotExist() {
        int id = 20;
        assertThrows(EmptyResultDataAccessException.class, () -> adminService.removeById(id));
    }
}