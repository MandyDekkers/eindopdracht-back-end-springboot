package nl.eindopdracht.bootcamp;

import nl.eindopdracht.bootcamp.payload.request.SignupRequest;
import nl.eindopdracht.bootcamp.service.AddressService;
import nl.eindopdracht.bootcamp.service.AppUserService;
import nl.eindopdracht.bootcamp.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseFiller implements CommandLineRunner {

    private final AuthorizationService authorizationService;

    @Autowired
    public DatabaseFiller(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void run(String... args) {

        Set<String> rollen = new HashSet<>();
        rollen.add("admin");

        SignupRequest admin = new SignupRequest();
        admin.setUsername("nick");
        admin.setEmail("nick@admin.nl");
        admin.setPassword("nicknick");
        admin.setFirstName("Nick");
        admin.setLastName("Stuivenberg");
        admin.setStreetName("Straatnaam");
        admin.setHouseNumber("10");
        admin.setPostalCode("1234AB");
        admin.setCity("Utrecht");
        admin.setRole(rollen);
        authorizationService.registerUser(admin);

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
        rollen.remove("admin");
        rollen.add("user");
        user.setRole(rollen);
        authorizationService.registerUser(user);

    }
}




