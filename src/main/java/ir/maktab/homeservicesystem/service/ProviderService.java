package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.ProviderDao;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
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
    public void saveNewProvider(Expert expert) {

        Optional<Expert> foundProvider = providerDao.findByUserNameAndPass(expert.getUserName(), expert.getPassword());
        if (foundProvider.isPresent()) {
            throw new RuntimeException("provider exist");
        } else {
            providerDao.save(expert);
        }

    }

    public void deleteProvider(Expert expert) {
        Optional<Expert> foundProvider = providerDao.findByUserNameAndPass(expert.getUserName(), expert.getPassword());
        if (foundProvider.isPresent()) {
            providerDao.delete(expert);
        } else {
            throw new RuntimeException("provider not exist");
        }
    }

    public void updateProvider(Expert expert, Double serviceRequestScore) {
        providerDao.update((expert));
    }

    public Expert findByUserNameAndPass(String username, String password) {
        Optional<Expert> provider = providerDao.findByUserNameAndPass(username, password);
        if (provider.isPresent()) {
            return provider.get();
        } else
            throw new RuntimeException("provider not exist");
    }


    public Expert findByEmail(String email) {
        Optional<Expert> foundProvider = providerDao.findByEmail(email);
        if (foundProvider.isPresent())
            return foundProvider.get();
        else
            throw new RuntimeException("provider not exist");
    }
    public List<Expert> findAll() {
        return providerDao.findAll();
    }
}
