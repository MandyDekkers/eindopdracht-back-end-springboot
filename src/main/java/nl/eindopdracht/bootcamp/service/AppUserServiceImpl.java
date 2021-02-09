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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppUserRepository appUserRepository;

//    @Autowired
//    public void setAppUserRepository(AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }

    @Autowired
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
    public Optional<AppUserResponse> getAppUsersById(long id) {
        if (appUserRepository.existsById(id)) {
            Optional<AppUser> appuser = this.appUserRepository.findById(id);
            AppUser app = appuser.get();
            return Optional.of(new AppUserResponse(app.getEmail(), app.getFirstName(), app.getEmail(),
                    app.getAddress().getStreetName(), app.getAddress().getHouseNumber(), app.getAddress().getPostalCode(),
                    app.getAddress().getCity()));
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void updateAppUser(long id, AppUserResponse appUser) {
        if (appUserRepository.existsById(id)) {
            try {
                AppUser existingAppUser = appUserRepository.findById(id).orElse(null);

                existingAppUser.setFirstName(appUser.getFirstName());
                existingAppUser.setLastName(appUser.getLastName());
                existingAppUser.setEmail(appUser.getEmail());

                Address oldAddress = existingAppUser.getAddress();
                oldAddress.setStreetName(appUser.getStreetName());
                oldAddress.setHouseNumber(appUser.getHouseNumber());
                oldAddress.setPostalCode(appUser.getPostalCode());
                oldAddress.setCity(appUser.getCity());

                appUserRepository.save(existingAppUser);
            } catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }

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

    @Override
    public void deleteAppUser(long id) {
        if (appUserRepository.existsById(id)) {
            appUserRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public AppUser getAppUserByLastName(String lastName) {
        return appUserRepository.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public ResponseEntity<?> addAppUser(AppUser appUser) {
        if (!appUserRepository.existsByEmail(appUser.getEmail())) {
            AppUser savedAppUser = appUserRepository.save(appUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAppUser);
        }
        return ResponseEntity.status(500).body("Email is not unique."); //response in controller
    }
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

    //    @Override
//    public AppUser getAppUsersById(long id) {
//        if (appUserRepository.existsById(id)) {
//            AppUser appuser = appUserRepository.findById(id).orElse(null);
//
//            AppUser userToReturn = new AppUser();
//
//            userToReturn.setFirstName(appuser.getFirstName());
//
//            return userToReturn;
//        } else {
//            throw new RecordNotFoundException();
//        }
//    }

