package nl.eindopdracht.bootcamp.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Username may not be empty")
    private String username;

    @NotBlank(message = "Password may not be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
