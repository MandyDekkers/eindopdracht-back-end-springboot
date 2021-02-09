package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;
import nl.eindopdracht.bootcamp.payload.response.ReservationDTO;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getLessonsByAppUser(long appUserId);
    List<ReservationDTO> getUsersByLesson(long lessonId);
    ReservationDTO getReservation(long appuserId, long lessonId);
    ReservationKey addReservation(long appuserId, long lessonId, Reservation reservation);
    ReservationKey addMember(long lessonId, long appuserId, Reservation reservation);
}
