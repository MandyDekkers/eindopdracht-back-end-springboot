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

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    LessonRepository lessonRepository;

    @Autowired
    public void setLessonRepository(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    AppUserRepository appUserRepository;

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    ReservationRepository reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDTO> getLessonsByAppUser(long appUserID) {
        if (appUserRepository.existsById(appUserID)) {
            return ((Collection<Reservation>) reservationRepository
                    .findAllByAppUserId(appUserID))
                    .stream()
                    .map(this::convertTo).collect(Collectors.toList());
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public List<ReservationDTO> getUsersByLesson(long lessonId) {
        if (lessonRepository.existsById(lessonId)) {
        return ((Collection<Reservation>) reservationRepository
                .findAllByLessonId(lessonId))
                .stream()
                .map(this::convertTo).collect(Collectors.toList());
    }
        else {
            throw new RecordNotFoundException();
        }
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
        newdto.setId(lesson.getId());
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
    public void deleteReservation(long appUserid, long lessonId) {
        ReservationKey reservationKey = new ReservationKey(appUserid, lessonId );
        if(!reservationRepository.existsById(reservationKey)) {
            throw  new RecordNotFoundException();
        }
        reservationRepository.deleteById(reservationKey);

    }

}
