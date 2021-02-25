package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class AppUserServiceTest {

    @InjectMocks
    AppUserServiceImpl appUserService;
    @Mock
    AppUserRepository appUserRepository;

    @Mock
    AppUser appUser;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        AppUser appuser = new AppUser();
        appuser.setId(1);
        appuser.setLastName("Dekkers");
    }


}
