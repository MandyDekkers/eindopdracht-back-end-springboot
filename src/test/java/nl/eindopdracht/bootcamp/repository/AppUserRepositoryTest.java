package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.service.AppUserServiceImpl;
import nl.eindopdracht.bootcamp.service.LessonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class AppUserRepositoryTest {

    @InjectMocks
    AppUserServiceImpl appUserService;
    @Mock
    AppUserRepository appUserRepository;
    @Mock
    AppUser appUser;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        appUser = new AppUser();
        appUser.setId(1);
        appUser.setFirstName("Mandy");
    }

//    @Test
//    public void whenLessonIdNotFound_thenReturnNull(){
//        Mockito.doReturn(null).when(appUserRepository)
//                .findById((long)100);
//
//        Optional<AppUserResponse> lesson1 = appUserService.getAppUsersById(appUser.getId());
//
//        assertNull(lesson1, "Not found");
//    }
//    @Test
//    void testAppUserById(){
//        when(appUserRepository.existsById((long) 1))
//                .thenReturn(true);
//
//        when(appUserRepository.findById((long) 1))
//                .thenReturn(Optional.of(appUser));
//
//        assertEquals(appUserService.getAppUsersById(1).get().getFirstName(), appUser.getFirstName());
//
//    }


}
