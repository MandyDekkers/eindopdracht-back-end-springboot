package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
