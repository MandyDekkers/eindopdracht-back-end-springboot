package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

        List<AppUser> findByLastNameIgnoreCase(String lastName);
        boolean existsByEmail(String email);
        Optional<AppUser> findByUsername(String username);
        Boolean existsByUsername(String username);


}
