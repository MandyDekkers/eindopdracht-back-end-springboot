package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUser> getAllAppUsers();
    AppUser getAppUsersById(long id);
    void updateAppUser(long id, AppUser appUser);
    void deleteAppUser(long id);
    AppUser getAppUserByLastName(String lastName);
    ResponseEntity<?> addAppUser(AppUser appUser);
    Optional<AppUser> getAppUser(String username);
}
