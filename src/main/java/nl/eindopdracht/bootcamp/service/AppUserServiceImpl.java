package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.DatabaseErrorException;
import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getAppUsersById(long id) {
        if (appUserRepository.existsById(id)) {
            return appUserRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveAppUser(AppUser appUser) {
        AppUser newAppUser = appUserRepository.save(appUser);
        return newAppUser.getAppUserid();
    }

    @Override
    public void updateAppUser(long id, AppUser appUser) {
        if (appUserRepository.existsById(id)) {
            try {
                AppUser existingAppUser = appUserRepository.findById(id).orElse(null);
                existingAppUser.setFirstName(appUser.getFirstName());
                existingAppUser.setLastName(appUser.getLastName());
                existingAppUser.setEmail(appUser.getEmail());
                existingAppUser.setPhoneNumber(appUser.getPhoneNumber());
                existingAppUser.setDateOfBirth(appUser.getDateOfBirth());
                appUserRepository.save(existingAppUser);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteAppUser(long id) {
        if (appUserRepository.existsById(id)) {
            appUserRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

}
