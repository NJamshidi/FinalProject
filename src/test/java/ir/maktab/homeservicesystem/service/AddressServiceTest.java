package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressServiceTest { //with builder
    @Autowired
    private AddressService addressService;

    @Test
    void save() {
        Address address = Address.builder()
                .province("p1")
                .city("C1")
                .street("S1")
                .alley("A1")
                .plaque("1")
                .build();

        Address saveAddressResult = addressService.save(address);
        assertNotNull(saveAddressResult);
    }
    @Test
    void loadById() {
        Address findResult = addressService.findById(1);
        assertNotNull(findResult);
    }
    @Test
    void loadByIdNotExist() {
        assertThrows(NotFoundObjectException.class, () -> addressService.findById(12));
    }

    @Test
    void loadAll() {
        List<Address> addressList = addressService.findAll();
        assertEquals(1, addressList.size());
    }

    @Test
    void delete(){
        addressService.removeById(1);
        List<Address> addressList = addressService.findAll();
        assertEquals(0, addressList.size());
    }

    @Test
    void deleteNotExist(){
        assertThrows(NotFoundObjectException.class, () -> addressService.removeById(5));
    }
}

