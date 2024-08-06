package com.alex96jvm.authservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationRequest {

    @NotBlank(message = "Логин не может быть пустым")
    @Size(max = 32, message = "Логин должен содержать не более 32 символов")
    private String login;

    @Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
