package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> getAllAppUsers();
    AppUser getAppUsersById(long id);
//    long saveAppUser(AppUser appUser);
    long addAppUser(AppUser appUser);
    void updateAppUser(long id, AppUser appUser);
    void deleteAppUser(long id);


}
