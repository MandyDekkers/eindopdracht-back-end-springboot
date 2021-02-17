package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
