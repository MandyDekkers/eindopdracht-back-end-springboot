package nl.eindopdracht.bootcamp.controller;

import nl.eindopdracht.bootcamp.payload.request.LoginRequest;
import nl.eindopdracht.bootcamp.payload.request.SignupRequest;
import nl.eindopdracht.bootcamp.payload.request.UpdateUserRequest;
import nl.eindopdracht.bootcamp.payload.response.JwtResponse;
import nl.eindopdracht.bootcamp.payload.response.MessageResponse;
import nl.eindopdracht.bootcamp.service.AppUserService;
import nl.eindopdracht.bootcamp.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthorizationService authorizationService;

    @Autowired
    public void setAuthorizationService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authorizationService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        return authorizationService.registerUser(signUpRequest);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestHeader Map<String, String> headers,
                                        @RequestBody UpdateUserRequest updateRequest) {
        return appUserService.updateUserById(headers.get("authorization"), updateRequest);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> findUserByToken(@RequestHeader Map<String, String> headers) {
        System.out.println(headers.get("authorization"));
        return appUserService.findUserByToken(headers.get("authorization"));
    }

}
