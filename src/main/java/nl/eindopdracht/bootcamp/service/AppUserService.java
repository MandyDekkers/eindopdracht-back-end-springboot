package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    List<AppUserResponse> getAllAppUsers();
    Optional<AppUserResponse> getAppUsersById(long id);
    //    void updateAppUser(long id, AppUser appUser);
    void updateAppUser(long id, AppUserResponse appUser);
    void deleteAppUser(long id);
    List<AppUserResponse> getAppUserByLastName(String lastName);

//    ResponseEntity<?> addAppUser(AppUser appUser);
}
