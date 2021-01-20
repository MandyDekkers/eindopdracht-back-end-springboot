package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

        AppUser findByLastNameIgnoreCase(String lastName);
        boolean existsByEmail(String email);

}
