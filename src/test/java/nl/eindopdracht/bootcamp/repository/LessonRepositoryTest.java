//package nl.eindopdracht.bootcamp.repository;
//
//import nl.eindopdracht.bootcamp.BootcampApplication;
//import nl.eindopdracht.bootcamp.model.Lesson;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@ContextConfiguration(classes={BootcampApplication.class})
//public class LessonRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private LessonRepository lessonRepository;
//
//    @Test
//    void testLessonFindById() {
//        Lesson lesson1 = new Lesson();
//        lesson1.setId(1);
//        lesson1.setMaxAmountMembers("25");
//        lesson1.setDate("maandag 2 Maart 2021");
//        lesson1.setNiveau("gevorderd");
//        lesson1.setName("Bootcamp");
//
//        entityManager.persist(lesson1);
//        entityManager.flush();
//
//        Optional<Lesson> lessonfound = lessonRepository.findById(lesson1.getId());
//
//        long expected = 1;
//        long actual = lessonfound.get().getId();
//        assertEquals(expected, actual);
//    }
//}
