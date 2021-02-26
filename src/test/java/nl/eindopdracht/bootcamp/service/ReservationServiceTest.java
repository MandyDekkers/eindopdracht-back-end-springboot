package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import nl.eindopdracht.bootcamp.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReservationServiceTest{

    @InjectMocks
    ReservationServiceImpl reservationService;

    @Mock
    ReservationRepository reservationRepository;
    @Mock
    Reservation reservation;

}