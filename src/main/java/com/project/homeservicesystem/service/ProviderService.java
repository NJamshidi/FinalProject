package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ProviderDao;
import com.project.homeservicesystem.entities.users.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ProviderService {
    private ProviderDao providerDao;
    public void saveNewProvider(Provider provider) {

        Optional<Provider> foundProvider = providerDao.findByUserNameAndPass(provider.getUserName(), provider.getPassword());
        if (foundProvider.isPresent()) {
            throw new RuntimeException("provider exist");
        } else {
            providerDao.save(provider);
        }

    }

    public void deleteProvider(Provider provider) {
        Optional<Provider> foundProvider = providerDao.findByUserNameAndPass(provider.getUserName(), provider.getPassword());
        if (foundProvider.isPresent()) {
            providerDao.delete(provider);
        } else {
            throw new RuntimeException("provider not exist");
        }
    }

    public void updateProvider(Provider provider, Double serviceRequestScore) {
        providerDao.update((provider));
    }

    public Provider  findByUserNameAndPass(String username, String password) {
        Optional<Provider> provider = providerDao.findByUserNameAndPass(username, password);
        if (provider.isPresent()) {
            return provider.get();
        } else
            throw new RuntimeException("provider not exist");
    }


    public Provider findByEmail(String email) {
        Optional<Provider> foundProvider = providerDao.findByEmail(email);
        if (foundProvider.isPresent())
            return foundProvider.get();
        else
            throw new RuntimeException("provider not exist");
    }
    public List<Provider> findAll() {
        return providerDao.findAll();
    }
}
