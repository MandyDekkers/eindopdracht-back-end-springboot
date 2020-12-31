package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Lesson;

import java.util.List;

public interface LessonService {

    List<Lesson> getAllLessons();
    Lesson getLessonById(long id);
    long saveLesson(Lesson lesson);
    void updateLesson(long id, Lesson lesson);
    void deleteLesson(long id);
}
