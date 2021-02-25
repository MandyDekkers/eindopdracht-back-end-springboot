package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.DatabaseErrorException;
import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private AppUserRepository appUserRepository;

    private LessonRepository lessonRepository; //wel of geen privater hiervoor?

    @Autowired
    public void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        if (!lessons.isEmpty()) {
            return lessonRepository.findAll();
        }
        throw new RecordNotFoundException();
    }

    @Override
    public Lesson getLessonById(long id) {
        if (lessonRepository.existsById(id)) {
            return lessonRepository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public long saveLesson(Lesson lesson) {
        Lesson newLesson = lessonRepository.save(lesson);
        return newLesson.getId();
    }

    @Override
    public void updateLesson(long id, Lesson lesson) {
        if (lessonRepository.existsById(id)) {
            try {
                Lesson existingLesson = lessonRepository.findById(id).orElse(null);
                existingLesson.setName(lesson.getName());
                existingLesson.setDate(lesson.getDate());
                existingLesson.setMaxAmountMembers(lesson.getMaxAmountMembers());
                existingLesson.setNiveau(lesson.getNiveau());
                lessonRepository.save(existingLesson);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteLesson(long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

}
