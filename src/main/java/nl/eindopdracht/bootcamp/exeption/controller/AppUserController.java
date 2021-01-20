package nl.eindopdracht.bootcamp.exeption.controller;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.service.AddressService;
import nl.eindopdracht.bootcamp.service.AppUserService;
import nl.eindopdracht.bootcamp.service.LessonService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppUserController {

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

    @GetMapping(value = "/appuser")
    public ResponseEntity<Object> getAllAppUsers(){
        List<AppUser> appUsers = appUserService.getAllAppUsers();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
        }

    @GetMapping(value = "/appuser/{id}")
    public ResponseEntity<Object> getAppUser(@PathVariable("id") long id) {
        AppUser appUser = appUserService.getAppUsersById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
        }

    @GetMapping(value = "/appuser/lastname/{lastName}")
    public ResponseEntity<Object> getAppUserByLastName(@PathVariable("lastName") String lastName) {
        AppUser appUser = appUserService.getAppUserByLastName(lastName);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping(value = "/appuser")
    public ResponseEntity<?> saveAppUser(@RequestBody AppUser appUser) {
        return appUserService.addAppUser(appUser);
        }
//PETER:
//    @PostMapping(value = "/appuser")
//    public ResponseEntity<Object> addAppUser(@RequestBody AppUser appUser) {
//        long newId = appUserService.addAppUser(appUser);
//        return new ResponseEntity<>(newId, HttpStatus.CREATED);
//    }

    @PutMapping(value = "/appuser/{id}")
    public ResponseEntity<Object> updateAppUser(@PathVariable("id") int id, @RequestBody AppUser appUser) {
        appUserService.updateAppUser(id, appUser);
        return new ResponseEntity<>("User is geupdated!", HttpStatus.OK);
        }

    @DeleteMapping(value = "/appuser/{id}")
    public ResponseEntity<Object> deleteAppUser(@PathVariable("id") long id) {
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>("User is verwijderd", HttpStatus.OK);
        }
    }
