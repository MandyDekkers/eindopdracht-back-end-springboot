package nl.eindopdracht.bootcamp.controller;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Reservation;
import nl.eindopdracht.bootcamp.model.ReservationKey;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.payload.response.ReservationDTO;
import nl.eindopdracht.bootcamp.service.AddressService;
import nl.eindopdracht.bootcamp.service.AppUserService;
import nl.eindopdracht.bootcamp.service.LessonService;
import nl.eindopdracht.bootcamp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "appuser")
@CrossOrigin(origins = "http://localhost:3000")

public class AppUserController {

    private ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Autowired
    private AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    private LessonService lessonService;

    @Autowired
    public void setLessonService(LessonService lessonService) {
        this.lessonService = lessonService;
    }

//    @GetMapping(value = "")
//    public ResponseEntity<Object> getAllAppUsers(){
//        List<AppUser> appUsers = appUserService.getAllAppUsers();
//        return new ResponseEntity<>(appUsers, HttpStatus.OK);
//    }

    //WERKT! RETOURNEERT DE APPUSERRESPONSE
    @GetMapping("")
    @ResponseBody
    public List<AppUserResponse> getAllAppUsers() {
        List <AppUserResponse> appUserResponses = appUserService.getAllAppUsers();
        return appUserResponses;
    }

    //GEEN APPUSERRESPONSE
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Object> getAppUser(@PathVariable("id") long id) {
//        AppUser appUser = appUserService.getAppUsersById(id);
//        return new ResponseEntity<>(appUser, HttpStatus.OK);
//    }

//WERKT, RETOURNEERT DE APPUSERRESPONSE
    @GetMapping(value = "/{id}")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(appUserService.getAppUsersById(id).get());
    }


    //LOOP, EN WAT ALS ER TWEE DEZELFDE LASTNAMES ZIJN?
    @GetMapping(value = "/lastname/{lastName}")
    public ResponseEntity<Object> getAppUserByLastName(@PathVariable("lastName") String lastName) {
        AppUser appUser = appUserService.getAppUserByLastName(lastName);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    //WERKT MET APPUSERRESPONSE KUNNEN NAW GEGEVENS GEUPDATE WORDEN DOOR USER
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateAppUser(@PathVariable("id") int id, @RequestBody AppUserResponse appUser) {
        appUserService.updateAppUser(id, appUser);
        return new ResponseEntity<>("User is geupdated!", HttpStatus.OK);
        }

    //WERKT
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAppUser(@PathVariable("id") long id) {
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>("User is verwijderd", HttpStatus.OK);
        }

// -------RESERVATION---------


    //get reservations per id appuser
//    @GetMapping(value = "/{id}/lessons")
//    public ResponseEntity<Object> getLessonsByAppUser(@PathVariable("id") long id) {
//        return ResponseEntity.ok().body(reservationService.getLessonsByAppUser(id));
//    }

    @GetMapping(value = "/{id}/lessons")
    public ResponseEntity<Object> getLessonsByAppUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(reservationService.getLessonsByAppUser(id));
    }


        //get specific reservation/lesson by id appuser MET RESERVATIONDTO
    @GetMapping(value = "/{appuser_id}/lessons/{lesson_id}")
    public ResponseEntity<Object> getReservation(@PathVariable("appuser_id") long appuserId,
                                                         @PathVariable("lesson_id") long lessonId) {
        return ResponseEntity.ok().body(reservationService.getReservation(appuserId, lessonId));
    }

        //geeft een reservation ID terug
    @PostMapping(value = "/{appuser_id}/lesson/{lesson_id}")
    public ResponseEntity<Object> makeReservation(@PathVariable("appuser_id") long appuserId,
                                                         @PathVariable("lesson_id") long lessonId,
                                                         @RequestBody Reservation reservation) {
        ReservationKey newId = reservationService.addReservation(appuserId, lessonId, reservation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

}
