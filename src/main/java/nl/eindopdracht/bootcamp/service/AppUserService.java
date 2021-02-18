package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.payload.request.UpdateUserRequest;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public interface AppUserService {

    List<AppUserResponse> getAllAppUsers();
    Optional<AppUserResponse> getAppUsersById(long id);
    //    void updateAppUser(long id, AppUser appUser);
    void updateAppUser(long id, AppUserResponse appUser);
    void deleteAppUser(long id);
    List<AppUserResponse> getAppUserByLastName(String lastName);

    ResponseEntity<?> updateUserById(String token, @Valid UpdateUserRequest appUserRequest);
    ResponseEntity<?> findUserByToken(String token);
}
