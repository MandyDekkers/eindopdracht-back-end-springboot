package nl.eindopdracht.bootcamp.service;

import nl.eindopdracht.bootcamp.exeption.RecordNotFoundException;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;
import nl.eindopdracht.bootcamp.payload.response.ReservationDTO;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.LessonRepository;
import nl.eindopdracht.bootcamp.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<ReservationDTO> getLessonsByAppUser(long appUserID) {
    return ((Collection<Reservation>) reservationRepository
            .findAllByAppUserId(appUserID))
            .stream()
            .map(this::convertTo).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getUsersByLesson(long lessonId) {
        return ((Collection<Reservation>) reservationRepository
                .findAllByLessonId(lessonId))
                .stream()
                .map(this::convertTo).collect(Collectors.toList());
    }

    private ReservationDTO convertTo(Reservation reservation) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ReservationDTO reservationDTO = modelMapper
                .map(reservation, ReservationDTO.class);

        return reservationDTO;
    }

    @Override
    public ReservationDTO getReservation(long appUserID, long lessonId) {
        if (!appUserRepository.existsById(appUserID)) { throw new RecordNotFoundException(); }
        AppUser appUser = appUserRepository.findById(appUserID).orElse(null);
        if (!lessonRepository.existsById(lessonId)) { throw new RecordNotFoundException(); }
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);

        Reservation reservation = reservationRepository.findById(new ReservationKey(appUserID, lessonId)).orElse(null);

        ReservationDTO newdto = new ReservationDTO();
        newdto.setFirstName(appUser.getFirstName());
        newdto.setLastName(appUser.getLastName());
        newdto.setName(lesson.getName());
        newdto.setDate(lesson.getDate());
        newdto.setMaxAmountMembers(lesson.getMaxAmountMembers());
        newdto.setNiveau(lesson.getNiveau());
        newdto.setComment(reservation.getComment());

        return newdto;
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
