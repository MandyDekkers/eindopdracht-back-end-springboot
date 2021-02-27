package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.ERole;
import nl.eindopdracht.bootcamp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}