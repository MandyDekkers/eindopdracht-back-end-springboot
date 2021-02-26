package nl.eindopdracht.bootcamp.controller;

import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;
import nl.eindopdracht.bootcamp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "appuser")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/{id}/lessons")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getLessonsByAppUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(reservationService.getLessonsByAppUser(id));
    }

    @GetMapping(value = "/{id}/appusers")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getUsersByLesson(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(reservationService.getUsersByLesson(id));
    }

    @GetMapping(value = "/{appuser_id}/lesson/{lesson_id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getReservation(@PathVariable("appuser_id") long appuserId,
                                                 @PathVariable("lesson_id") long lessonId) {
        return ResponseEntity.ok().body(reservationService.getReservation(appuserId, lessonId));
    }

    @PostMapping(value = "/{appuser_id}/lesson/{lesson_id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> makeReservation(@PathVariable("appuser_id") long appuserId,
                                                  @PathVariable("lesson_id") long lessonId,
                                                  @RequestBody Reservation reservation) {
        ReservationKey newId = reservationService.addReservation(appuserId, lessonId, reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return new ResponseEntity<>("Les is reserved!", HttpStatus.OK);
    }

}
