package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.user.UserChangePasswordEntity;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordResult;
import ir.maktab.homeservicesystem.dto.user.customer.*;
import ir.maktab.homeservicesystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerCreateResult> save(@RequestBody CustomerCreateEntity customerCreateEntity) {
        CustomerCreateResult customerCreateResult= customerService.saveCustomer(customerCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreateResult);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserChangePasswordResult> changePassword(@RequestBody UserChangePasswordEntity changePasswordEntity, @PathVariable int id) {
        changePasswordEntity.setUserId(id);
        UserChangePasswordResult userChangePasswordResult = customerService.changePassword(changePasswordEntity);
        return ResponseEntity.ok(userChangePasswordResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerUpdateResult> update(@PathVariable int id, @RequestBody CustomerUpdateEntity customerUpdateEntity) {

        customerUpdateEntity.setId(id);

        CustomerUpdateResult customerUpdateResult = customerService.updateCustomer(customerUpdateEntity);
        return ResponseEntity.ok(customerUpdateResult);
    }
}

