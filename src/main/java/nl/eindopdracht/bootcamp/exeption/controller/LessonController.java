package nl.eindopdracht.bootcamp.exeption.controller;

import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping(value = "/lesson")
    public ResponseEntity<Object> getLessons(){
        List<Lesson> lessons = lessonService.getAllLessons();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping(value = "/lesson/{id}")
    public ResponseEntity<Object> getLesson(@PathVariable("id") long id) {
        Lesson lesson = lessonService.getLessonById(id);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @PostMapping(value = "/lesson")
    public ResponseEntity<Object> saveLesson(@RequestBody Lesson lesson) {
        long newId = lessonService.saveLesson(lesson);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/lesson/{id}")
    public ResponseEntity<Object> updateLesson(@PathVariable("id") int id, @RequestBody Lesson lesson) {
        lessonService.updateLesson(id, lesson);
        return new ResponseEntity<>("Les is geupdated!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/lesson/{id}")
    public ResponseEntity<Object> deleteLesson(@PathVariable("id") long id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>("Les is verwijderd", HttpStatus.OK);
    }

}
