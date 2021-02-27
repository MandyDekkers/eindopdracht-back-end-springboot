package nl.eindopdracht.bootcamp.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestLesson {

    private Lesson lesson;

    @Before
    public void setUp() {
        this.lesson = new Lesson();
        lesson.setNiveau("gevorderd");
    }

    @Test
    public void testGetNiveauLesson() {
        String expectedNiveau = "gevorderd";
        String actualNiveau = this.lesson.getNiveau();
        assertThat(actualNiveau).isEqualTo(expectedNiveau);
    }

}
