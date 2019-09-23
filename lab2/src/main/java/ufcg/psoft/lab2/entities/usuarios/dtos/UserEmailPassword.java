package ufcg.psoft.lab2.entities.usuarios.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UserEmailPassword {

    private String email;
    private String password;

    @JsonCreator
    public UserEmailPassword(String email, String password) {
        this.email = email;
        this.password = password;
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
}
