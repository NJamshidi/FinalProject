package ir.maktab.homeservicesystem.controller;


import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.dto.CustomerDto;
import ir.maktab.homeservicesystem.dto.mapper.UserChangePasswordParam;
import ir.maktab.homeservicesystem.dto.user.UserChangePasswordResult;
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
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserChangePasswordResult> changePassword(@RequestBody UserChangePasswordParam changePasswordParam,@PathVariable int id) {
        changePasswordParam.setUserId(id);
        UserChangePasswordResult userChangePasswordResult = customerService.changePassword(changePasswordParam);
        return ResponseEntity.ok(userChangePasswordResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable int id, @RequestBody CustomerDto customerDto) {

        CustomerDto customerDto1 = customerService.loadByIdReturnDto(id);
        int addressId = customerDto1.getAddress().getId();
        Address address = customerDto.getAddress();
        address.setId(addressId);
        customerDto.setAddress(address);

        customerDto.setId(id);

        CustomerDto customerUpdateResult = customerService.updateCustomer(customerDto);
        return ResponseEntity.ok(customerUpdateResult);
    }
}

