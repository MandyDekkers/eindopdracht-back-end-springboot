package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
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

    @Test
    void testDeleteLesson(){
        Mockito.when(lessonRepository.existsById(anyLong())).thenReturn(true);
        lessonService.deleteLesson(1L);
        Mockito.verify(lessonRepository, times(1)).deleteById(1L);
    }

    @Test
    public void saveLesson() {
        Lesson lesson = new Lesson();
        lesson.setName("testles");
        lesson.setId(1);
        lesson.setMaxAmountMembers("3");
        lesson.setDate("maandag 1 maart");

        long id = 1;

        Mockito
                .when(lessonRepository.save(lesson))
                .thenReturn(lesson);

        long result = lessonService.saveLesson(lesson);
    }
    
    @Test
    public void testFindAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        assertThat(lessons.size(), is(greaterThanOrEqualTo(0)));
    }

        private void assertThat(int size, ElementMatcher.Junction<Object> objectJunction) {
    }


}
