package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.DatabaseErrorException;
import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(long id) {
        if (lessonRepository.existsById(id)) {
            return lessonRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveLesson(Lesson lesson) {
        Lesson newLesson = lessonRepository.save(lesson);
        return newLesson.getLessonId();
    }

    @Override
    public void updateLesson(long id, Lesson lesson) {
        if (lessonRepository.existsById(id)) {
            try {
                Lesson existingLesson = lessonRepository.findById(id).orElse(null);
                existingLesson.setLocation(lesson.getLocation());
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
