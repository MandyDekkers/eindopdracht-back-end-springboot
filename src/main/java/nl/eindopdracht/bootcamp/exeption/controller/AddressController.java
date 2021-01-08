package nl.eindopdracht.bootcamp.exeption.controller;

import nl.eindopdracht.bootcamp.model.Address;
import nl.eindopdracht.bootcamp.model.AppUser;
import nl.eindopdracht.bootcamp.model.Lesson;
import nl.eindopdracht.bootcamp.repository.AddressRepository;
import nl.eindopdracht.bootcamp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/address")
    public ResponseEntity<Object> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping(value = "/address/{id}")
    public ResponseEntity<Object> getAddress(@PathVariable("id") long id) {
        Address address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping(value = "/address")
    public ResponseEntity<Object> saveAddress(@RequestBody Address address) {
        long newId = addressService.saveAddress(address);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/address/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable("id") int id, @RequestBody Address address) {
        addressService.updateAddress(id, address);
        return new ResponseEntity<>("Adres is geupdated!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/address/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable("id") long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Adres is verwijderd", HttpStatus.OK);
    }


}
