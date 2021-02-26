package nl.eindopdracht.bootcamp.repository;

import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationKey> {

    Collection<Reservation> findAllByAppUserId(long appUserId);
    Collection<Reservation> findAllByLessonId(long lessonId);
    void deleteById(Reservation reservation);
}
