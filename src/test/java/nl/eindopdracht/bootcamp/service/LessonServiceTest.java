package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class LessonServiceTest {

    @InjectMocks
    LessonServiceImpl lessonService;
    @Mock
    LessonRepository lessonRepository;
    @Mock
    Lesson lesson;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        lesson = new Lesson();
        lesson.setId(1);
        lesson.setMaxAmountMembers("25");
        lesson.setDate("maandag 2 Maart 2021");
        lesson.setNiveau("gevorderd");
        lesson.setName("Bootcamp");
    }

    @Test
    void testGetLessonById() {
        when(lessonRepository.existsById((long) 1))
                .thenReturn(true);

        when(lessonRepository.findById((long) 1))
                .thenReturn(Optional.of(lesson));

        assertEquals(lessonService.getLessonById(1).getName(), lesson.getName());
    }


    @Test
    public void whenLessonIdNotFound_thenReturnNull(){
        Mockito.doReturn(null).when(lessonRepository)
                .findById((long)100);

        Lesson lesson1 = lessonService.getLessonById(lesson.getId());

        assertNull(lesson1, "Not found");
    }


}
