package com.project.homeservicesystem.service;

import com.project.homeservicesystem.dao.ProviderDao;
import com.project.homeservicesystem.entities.users.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderService {
    private ProviderDao providerDao;
    public void saveNewProvider(Provider provider) {
        providerDao.save(provider);
    }

}
