package nl.eindopdracht.bootcamp;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.payload.request.SignupRequest;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        rollen.add("user");

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
        user.setRole(rollen);
        authorizationService.registerUser(user);

//        SignupRequest superuser = new SignupRequest();
//        superuser.setUsername("superuser");
//        superuser.setEmail("super@user.nl");
//        superuser.setPassword("123456");
//        rollen.add("admin");
//        superuser.setRole(rollen);
//        authorizationService.registerUser(superuser);

    }
}




