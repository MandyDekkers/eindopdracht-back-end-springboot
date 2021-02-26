package nl.eindopdracht.bootcamp.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

    @NotBlank(message = "Username may not be empty")
    @Size(min = 3, max = 20, message = "Name must be between 2 and 20 characters long")
    private String username;

    @NotBlank(message = "Email may not be empty")
    @Size(max = 50)
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotBlank(message = "Password may not be empty")
    @Size(min = 3, max = 32, message = "Password must be between 6 and 20 characters long")
    private String password;

    @NotBlank(message = "Password may not be empty")
    @Size(min = 3, max = 32, message = "Password must be between 6 and 20 characters long")
    private String repeatedPassword;

    @NotBlank(message = "Firstname may not be empty")
    private String firstName;

    @NotBlank(message = "LastName may not be empty")
    private String lastName;

    @NotBlank(message = "Streetname may not be empty")
    private String streetName;

    @NotBlank(message = "Housenumber may not be empty")
    private String houseNumber;

    @NotBlank(message = "Postalcode may not be empty")
    private String postalCode;

    @NotBlank(message = "City may not be empty")
    private String city;

    private Set<String> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}

