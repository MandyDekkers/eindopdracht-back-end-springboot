package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

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

    @Test
    void testDeleteAppUser(){
        Mockito.when(appUserRepository.existsById(anyLong())).thenReturn(true);
        appUserService.deleteAppUser(1L);
        Mockito.verify(appUserRepository, times(1)).deleteById(1L);
    }

}
