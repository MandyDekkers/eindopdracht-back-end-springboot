package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {

    List<AppUserResponse> getAllAppUsers();
    AppUser getAppUsersById(long id);
    void updateAppUser(long id, AppUser appUser);
    void deleteAppUser(long id);
    AppUser getAppUserByLastName(String lastName);
    ResponseEntity<?> addAppUser(AppUser appUser);
}
