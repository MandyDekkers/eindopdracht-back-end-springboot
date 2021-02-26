package nl.eindopdracht.bootcamp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import nl.eindopdracht.bootcamp.exeption.DatabaseErrorException;
import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.payload.request.UpdateUserRequest;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.payload.response.MessageResponse;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.ReservationRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private AppUserRepository appUserRepository;

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    private ReservationRepository reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Value("${novi.sec.jwtSecret}")
    private String jwtSecret;

    private static final String PREFIX = "Bearer ";

    private PasswordEncoder encoder;

    @Override
    public long saveImage(AppUser appUser) {

        Optional<AppUser> appUserOptional =  appUserRepository.findByUsername(appUser.getUsername());

        if(appUserOptional.isPresent() && !appUser.getImage().equals("")) {
            AppUser foundUser = appUserOptional.get();

            foundUser.setImage(appUser.getImage());
            return appUserRepository.save(foundUser).getId();
        }

        throw new RuntimeException("User or image not found");
    }

    @Override
    public ResponseEntity<?> updateUserById(String token, UpdateUserRequest userRequest) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
        }
        String username = getUsernameFromToken(token);

        if (userExists(username) && updateRequestIsValid(userRequest)) {
            AppUser updatedUser = findUserByUsername(username);
            if (!userRequest.getPassword().isEmpty() && !userRequest.getRepeatedPassword().isEmpty()) {
                updatedUser.setPassword(encoder.encode(userRequest.getPassword()));
            }

            appUserRepository.save(updatedUser);

            return ResponseEntity.ok().body(new MessageResponse("Details are uptdated!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("User cannot be updated with provided data."));
    }

    @Override
    public ResponseEntity<?> findUserByToken(String token) {
        String username = getUsernameFromToken(token);

        if (userExists(username)) {
            AppUser appUserFound = findUserByUsername(username);
            AppUser appUserToReturn = new AppUser();
            appUserToReturn.setId(appUserFound.getId());
            appUserToReturn.setUsername(appUserFound.getUsername());
            appUserToReturn.setEmail(appUserFound.getEmail());

            return ResponseEntity.ok(appUserToReturn);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
    }

    @Override
    public long saveAppUser(AppUser appUser) {
        AppUser newAppUser = appUserRepository.save(appUser);
        return newAppUser.getId();
    }

    private String getUsernameFromToken(String token) {
        String tokenWithoutBearer = removePrefix(token);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(tokenWithoutBearer).getBody();

        return claims.getSubject();
    }

    private String removePrefix(String token) {
        return token.replace(PREFIX, "");
    }

    private boolean userExists(String username) {
        return appUserRepository.existsByUsername(username);
    }

    private boolean updateRequestIsValid(UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.getPassword().equals(updateUserRequest.getRepeatedPassword())) {
            return true;
        }
        return false;
    }

    private AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username).get();
    }

    @Autowired
    public void setUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public List<AppUserResponse> getAllAppUsers() {

        List<AppUser> users = appUserRepository.findAll();

        if (users.isEmpty()) {
            return (List<AppUserResponse>) ResponseEntity.badRequest().body(new MessageResponse("No Users found!"));

        }
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

        return appUserResponse;
    }

    @Override
    public Optional<AppUserResponse> getAppUsersById(long id) {
        if (appUserRepository.existsById(id)) {
            Optional<AppUser> appuser = this.appUserRepository.findById(id);

            AppUser app = appuser.get();
            return Optional.of(new AppUserResponse(app.getId(), app.getEmail(), app.getFirstName(), app.getLastName(),
                    app.getAddress().getStreetName(), app.getAddress().getHouseNumber(), app.getAddress().getPostalCode(),
                    app.getAddress().getCity(), app.getImage()));
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

    @Override
    public void deleteAppUser(long id) {
        if (appUserRepository.existsById(id)) {
            appUserRepository.deleteById(id);

        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<AppUserResponse> getAppUserByLastName(String lastName) {
        try {
            return ((List<AppUser>) appUserRepository
                    .findByLastNameIgnoreCase(lastName))
                    .stream()
                    .map(this::convertToAppUserResponse).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new RecordNotFoundException();
        }
    }

}
