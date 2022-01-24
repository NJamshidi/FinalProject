package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.service.CustomerService;
import ir.maktab.homeservicesystem.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin"})
public class AdminController {

    @GetMapping
    public String getAdminPage(Model m) {
        Expert expert = new Expert();
        m.addAttribute(expert);
        return "admin";
    }


    @GetMapping("/confirmExpert")
    public String getConfirmExpertPage() {
        return "confirmExpert";
    }

    @GetMapping("/manageService")
    public String getManageServicePage() {
        return "manageService";
    }

    @GetMapping("/expertReport")
    public String getExpertReportPage() {
        return "expertReport";
    }

    @GetMapping("/userReport")
    public String getUserReportPage() {
        return "userReport";
    }
}
