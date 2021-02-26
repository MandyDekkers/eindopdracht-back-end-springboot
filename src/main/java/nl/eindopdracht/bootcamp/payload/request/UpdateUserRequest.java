package nl.eindopdracht.bootcamp.payload.request;

import javax.validation.constraints.Size;

public class UpdateUserRequest {

    @Size(min = 6, max = 40)
    private String password;

    @Size(min = 6, max = 40)
    private String repeatedPassword;

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
}