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

    @Autowired
    AppUserService appUserService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AddressService addressService;

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


        Set<String> rollen = new HashSet<>();
        rollen.add("user");

        SignupRequest user = new SignupRequest();
        user.setUsername("sjaak");
        user.setEmail("sjaak@sjaak.nl");
        user.setPassword("sjaaksjaak");
        user.setFirstName("Sjaak");
        user.setLastName("Klaassen");
        user.setStreetName("Straatnaam");
        user.setHouseNumber("25");
        user.setPostalCode("5678CD");
        user.setCity("Amsterdam");
        rollen.add("user");
        user.setRole(rollen);
        authorizationService.registerUser(user);
    }
}




