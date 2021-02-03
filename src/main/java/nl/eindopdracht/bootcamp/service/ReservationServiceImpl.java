package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import nl.eindopdracht.bootcamp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Collection<Reservation> getReservationsById(long appUserId) {
        return reservationRepository.findAllByAppUserId(appUserId);
    }

    @Override
    public Reservation getResultById(long studentId, long courseId) {
        return reservationRepository.findById(new ReservationKey(studentId, courseId)).orElse(null);
    }

    @Override
    public ReservationKey addReservation(long appuserId, long lessonId, Reservation reservation) {
        if (!appUserRepository.existsById(appuserId)) { throw new RecordNotFoundException(); }
        AppUser appUser = appUserRepository.findById(appuserId).orElse(null);
        if (!lessonRepository.existsById(lessonId)) { throw new RecordNotFoundException(); }
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        reservation.setAppUser(appUser);
        reservation.setLesson(lesson);
        ReservationKey id = new ReservationKey(appuserId, lessonId);
        reservation.setId(id);
        reservationRepository.save(reservation);
        return id;
    }

    @Override
    public ReservationKey addMember(long appuserId, long lessonId, Reservation reservation) {
        if (!appUserRepository.existsById(appuserId)) { throw new RecordNotFoundException(); }
        AppUser appUser = appUserRepository.findById(appuserId).orElse(null);
        if (!lessonRepository.existsById(lessonId)) { throw new RecordNotFoundException(); }
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        reservation.setAppUser(appUser);
        reservation.setLesson(lesson);
        ReservationKey id = new ReservationKey(appuserId, lessonId);
        reservation.setId(id);
        reservationRepository.save(reservation);
        return id;
    }

}
