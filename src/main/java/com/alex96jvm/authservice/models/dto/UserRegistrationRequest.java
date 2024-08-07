package com.alex96jvm.authservice.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationRequest that = (UserRegistrationRequest) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
