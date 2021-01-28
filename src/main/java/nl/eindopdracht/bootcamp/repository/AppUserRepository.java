package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

        AppUser findByLastNameIgnoreCase(String lastName);
        boolean existsByEmail(String email);
        Optional<AppUser> findByUsername(String username);
        Boolean existsByUsername(String username);
//
//        Optional<User> findByUsername(String username);
//        Boolean existsByUsername(String username);
//        Boolean existsByEmail(String email);



}
