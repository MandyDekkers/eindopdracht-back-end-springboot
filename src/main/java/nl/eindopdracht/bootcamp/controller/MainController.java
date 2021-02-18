package nl.eindopdracht.bootcamp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainController {

    @GetMapping(value = "/")
    public ResponseEntity<Object> sayHello() {
        return new ResponseEntity<>("Hello from demo_spring_jpa", HttpStatus.OK);
    }
}
