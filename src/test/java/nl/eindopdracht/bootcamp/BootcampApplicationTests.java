package nl.eindopdracht.bootcamp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ContextConfiguration(classes={BootcampApplication.class})
class BootcampApplicationTests {

	@Test
	@DisplayName("Testing if context is correctly set")
	void ContextLoadsTest() {
		assertNotEquals(1, 2);
	}
}
