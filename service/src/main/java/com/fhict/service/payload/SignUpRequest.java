package com.fhict.service.payload;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotNull
    @Size(min = 4, max = 40)
    private String name;

    @NotNull
    @Size(min = 3, max = 15)
    private String username;

    @NotNull
    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
