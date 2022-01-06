package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ProviderDao;
import com.project.homeservicesystem.entities.users.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@RequiredArgsConstructor
public class ProviderService {
    private ProviderDao providerDao;
    public void saveNewProvider(Provider provider) {
        providerDao.save(provider);
    }

}
