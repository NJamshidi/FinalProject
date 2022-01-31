package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.user.customer.CustomerList;
import ir.maktab.homeservicesystem.dto.user.expert.ExpertList;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import ir.maktab.homeservicesystem.service.CustomerService;
import ir.maktab.homeservicesystem.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final CustomerService customerService;
    private final ExpertService expertService;


    @GetMapping("/{id}/customers")
    public ResponseEntity<CustomerList> showAllCustomers(@PathVariable int id) {
        CustomerList customerList = customerService.findAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}/customers/{status}")
    public ResponseEntity<CustomerList> showAllCustomersByStatus(@PathVariable UserStatus status,@PathVariable int id) {
        CustomerList customerList = customerService.findAllCustomersByStatus(status);
        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/{id}/experts")
    public ResponseEntity<ExpertList> showAllExperts(@PathVariable int id) {
        ExpertList expertList = expertService.findAllExperts();
        return ResponseEntity.ok(expertList);
    }

    @GetMapping("/{id}/experts/{status}")
    public ResponseEntity<ExpertList> showAllExpertsByStatus(@PathVariable UserStatus status,@PathVariable int id) {
        ExpertList expertList = expertService.findAllExpertsByStatus(status);
        return ResponseEntity.ok(expertList);
    }

    @GetMapping("/{id}/experts/subService/{subServiceId}")
    public ResponseEntity<ExpertList> showAllExpertsBySubServiceId(@PathVariable int subServiceId,@PathVariable int id) {
        ExpertList expertList = expertService.findExpertsBySubServiceId(subServiceId);
        return ResponseEntity.ok(expertList);
    }
}