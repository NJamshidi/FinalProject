/*
package ir.maktab.homeservicesystem.controller;


import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.dto.CustomerDto;
import ir.maktab.homeservicesystem.dto.mapper.CustomerMapper;
import ir.maktab.homeservicesystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerDto customerDto;
    CustomerMapper customerMapper;

    @PostMapping
    public Customer save(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerService.save(customer);
        return customerService.save(customer);
    }
}*/
