package nl.eindopdracht.bootcamp.model;

import nl.eindopdracht.bootcamp.payload.request.SignupRequest;

public class AppUserBuilder {

    //AppUser
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    //Address
    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;

    //Role
    private ERole name;

    public AppUserBuilder(SignupRequest signupRequest) {
        this.email = signupRequest.getEmail();
        this.firstName = signupRequest.getFirstName();
        this.lastName = signupRequest.getLastName();
        this.username = signupRequest.getUsername();
        this.password = signupRequest.getPassword();
        this.streetName = signupRequest.getStreetName();
        this.houseNumber = signupRequest.getHouseNumber();
        this.postalCode = signupRequest.getPostalCode();
        this.city = signupRequest.getCity();
    }

    public AppUser buildAppUser(){
        return new AppUser(email, firstName, lastName, username, password);
    }

    public Address buildAddress(){
        return new Address(streetName, houseNumber, postalCode, city);
    }
}

