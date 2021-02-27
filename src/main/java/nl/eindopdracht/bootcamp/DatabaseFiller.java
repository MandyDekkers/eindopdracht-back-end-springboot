package nl.eindopdracht.bootcamp;

import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.ERole;
import nl.eindopdracht.bootcamp.model.Role;
import nl.eindopdracht.bootcamp.payload.request.SignupRequest;
import nl.eindopdracht.bootcamp.repository.RoleRepository;
import nl.eindopdracht.bootcamp.service.AddressService;
import nl.eindopdracht.bootcamp.service.AppUserService;
import nl.eindopdracht.bootcamp.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseFiller implements CommandLineRunner {

    private final AuthorizationService authorizationService;
    private PasswordEncoder encoder;

    @Autowired
    public DatabaseFiller(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    private AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        AppUser admin = new AppUser();

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();
        roles.add(adminRole);

        admin.setRoles(roles);
        admin.setUsername("nick");
        admin.setEmail("nick@admin.nl");
        admin.setPassword("nicknick");
        admin.setFirstName("Nick");
        admin.setLastName("Stuivenberg");

        Address adminaddress = new Address();
        adminaddress.setStreetName("Straatnaam");
        adminaddress.setHouseNumber("10");
        adminaddress.setPostalCode("5555HT");
        adminaddress.setCity("Utrecht");

        admin.setPassword(encoder.encode(admin.getPassword()));

        addressService.saveAddress(adminaddress);
        admin.setAddress(adminaddress);

        appUserService.saveAppUser(admin);

        AppUser user = new AppUser();

        Set<Role> rollen = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
        rollen.add(userRole);

        user.setRoles(rollen);
        user.setUsername("sjaak");
        user.setEmail("sjaak@sjaak.nl");
        user.setPassword("sjaaksjaak");
        user.setFirstName("Sjaak");
        user.setLastName("Klaassen");
        user.setImage("data:image/png;base64,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");

        Address useraddress = new Address();
        useraddress.setStreetName("Straatnaam");
        useraddress.setHouseNumber("10");
        useraddress.setPostalCode("5555HT");
        useraddress.setCity("Utrecht");

        user.setPassword(encoder.encode(user.getPassword()));

        addressService.saveAddress(useraddress);
        user.setAddress(useraddress);

        appUserService.saveAppUser(user);



//        Set<String> rollen = new HashSet<>();
//        rollen.add("user");
//
//        SignupRequest user = new SignupRequest();
//        user.setUsername("sjaak");
//        user.setEmail("sjaak@sjaak.nl");
//        user.setPassword("sjaaksjaak");
//        user.setFirstName("Sjaak");
//        user.setLastName("Klaassen");
//        user.setStreetName("Straatnaam");
//        user.setHouseNumber("25");
//        user.setPostalCode("5678CD");
//        user.setCity("Amsterdam");
//        rollen.add("user");
//        user.setRole(rollen);
//        authorizationService.registerUser(user);
    }
}




