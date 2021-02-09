package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {


    List<Lesson> findAllById(long userId);
}




