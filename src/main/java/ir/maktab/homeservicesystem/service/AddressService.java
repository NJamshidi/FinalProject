package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.AddressDao;
import ir.maktab.homeservicesystem.data.entities.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AddressService extends BaseService<Address, Integer> {
    private final AddressDao addressDao;

    @PostConstruct
    public void init() {
        setJpaRepository(addressDao);
    }
}