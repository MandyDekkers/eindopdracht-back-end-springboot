package nl.eindopdracht.bootcamp.model;

import nl.eindopdracht.bootcamp.payload.request.SignupRequest;

public class AppUserBuilder {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;

    public AppUserBuilder(SignupRequest signupRequest) {
        this.username = signupRequest.getUsername();
        this.email = signupRequest.getEmail();
        this.password = signupRequest.getPassword();
        this.firstName = signupRequest.getFirstName();
        this.lastName = signupRequest.getLastName();
        this.streetName = signupRequest.getStreetName();
        this.houseNumber = signupRequest.getHouseNumber();
        this.postalCode = signupRequest.getPostalCode();
        this.city = signupRequest.getCity();
    }

    public AppUser buildAppUser(){
        return new AppUser(username, email, password, firstName, lastName);
    }

    public Address buildAddress(){
        return new Address(streetName, houseNumber, postalCode, city);
    }
}

