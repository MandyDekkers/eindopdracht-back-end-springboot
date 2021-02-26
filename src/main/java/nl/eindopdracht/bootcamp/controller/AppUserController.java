package nl.eindopdracht.bootcamp.controller;

import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.payload.response.AppUserResponse;
import nl.eindopdracht.bootcamp.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import java.util.List;

@RestController
@RequestMapping(value = "appuser")
@CrossOrigin(origins = "*", maxAge = 3600)

public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "/image")
    public ResponseEntity<Object> saveImage(@RequestBody AppUser appUser) {
        long newId = appUserService.saveImage(appUser);
        return new ResponseEntity<>("Image is saved!", HttpStatus.CREATED);
    }

    @GetMapping("")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public List<AppUserResponse> getAllAppUsers() {
        List <AppUserResponse> appUserResponses = appUserService.getAllAppUsers();
        return appUserResponses;
    }

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(appUserService.getAppUsersById(id).get());
    }

    @GetMapping(value = "/lastname/{lastName}")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public List<AppUserResponse> getAppUserByLastName(@PathVariable("lastName") String lastName) {
        List<AppUserResponse> appusers = appUserService.getAppUserByLastName(lastName);
        return appusers;
    }

    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateAppUser(@PathVariable("id") int id, @RequestBody AppUserResponse appUser) {
        appUserService.updateAppUser(id, appUser);
        return new ResponseEntity<>("User is geupdated!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> deleteAppUser(@PathVariable("id") long id) {
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>("User is verwijderd", HttpStatus.OK);
    }

}