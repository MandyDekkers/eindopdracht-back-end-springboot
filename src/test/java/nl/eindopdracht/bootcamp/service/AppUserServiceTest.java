package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        appUser = new AppUser();
        appUser.setId(1);
        appUser.setFirstName("Voornaam");
        appUser.setLastName("Achternaam");
        appUser.setEmail("email@email.com");
    }

    @Test
    void testUpdateUser() {

        when(appUserRepository.existsById((long) 1))
                .thenReturn(true);

        when(appUserRepository.findById((long) 1))
                .thenReturn(Optional.of(appUser));

        assertEquals(appUserService.getAppUsersById(1).get().getId(), appUser.getId());
    }
}
