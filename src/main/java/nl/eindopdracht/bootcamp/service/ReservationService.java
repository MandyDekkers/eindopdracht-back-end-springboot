package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;

import java.util.Collection;

public interface ReservationService {

    Collection<Reservation> getReservationsById(long appUserId);
    Reservation getResultById(long appuserId, long lessonId);
    ReservationKey addReservation(long appuserId, long lessonId, Reservation reservation);
    ReservationKey addMember(long lessonId, long appuserId, Reservation reservation);
}
