package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.DatabaseErrorException;
import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private ModelMapper modelMapper;

    private AppUserRepository appUserRepository;

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


//    @Override
//    public List<AppUser> getAllAppUsers() {
//        return appUserRepository.findAll();
//    }

    public List<AppUserResponse> getAllAppUsers() {
        return ((List<AppUser>) appUserRepository
                .findAll())
                .stream()
                .map(this::convertToAppUserResponse).collect(Collectors.toList());
    }

    private AppUserResponse convertToAppUserResponse(AppUser appUser) {
    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
            AppUserResponse appUserResponse = modelMapper
                    .map(appUser, AppUserResponse.class);

//        AppUserResponse appUserResponse = new AppUserResponse();
//        appUserResponse.setEmail(appUser.getEmail());
//        appUserResponse.setFirstName(appUser.getFirstName());
//        appUserResponse.setLastName(appUser.getLastName());
//
//        Address address = appUser.getAddress();
//        appUserResponse.setStreetName(address.getStreetName());
//        appUserResponse.setHouseNumber(address.getHouseNumber());
//        appUserResponse.setPostalCode(address.getPostalCode());
//        appUserResponse.setCity(address.getCity());

        return appUserResponse;
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
    public void updateAppUser(long id, AppUser appUser) {
        if (appUserRepository.existsById(id)) {
            try {
                AppUser existingAppUser = appUserRepository.findById(id).orElse(null);
                existingAppUser.setFirstName(appUser.getFirstName());
                existingAppUser.setLastName(appUser.getLastName());
                existingAppUser.setEmail(appUser.getEmail());
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

    @Override
    public AppUser getAppUserByLastName(String lastName) {
        return appUserRepository.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public ResponseEntity<?> addAppUser(AppUser appUser) {
        if(!appUserRepository.existsByEmail(appUser.getEmail())){
            AppUser savedAppUser = appUserRepository.save(appUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAppUser);
        }
        return ResponseEntity.status(500).body("Email is not unique."); //response in controller
    }

//PETER:
    //    @Override
//    public long addAppUser(AppUser appUser) {
//        if(!appUserRepository.existsByEmail(appUser.getEmail())) {
//            AppUser newAppUser = appUserRepository.save(appUser);
//            return newAppUser.getId();
//        }
//        else
//            throw new RecordNotFoundException();
//        }

}
