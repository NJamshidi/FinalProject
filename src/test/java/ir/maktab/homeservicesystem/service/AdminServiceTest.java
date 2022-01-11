package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.users.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    @Test
    void test_save() {
        Admin admin = new Admin();
        admin.setFirstName("narges");
        admin.setLastName("jam");
        admin.setEmail("narges@mail.com");
        admin.setPassword("123asd45");

        Admin result = adminService.save(admin);
        assertNotNull(result);
    }
}