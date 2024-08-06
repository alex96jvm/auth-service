package com.alex96jvm.authservice.models.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String login;
    private String password;
    @JoinColumn(name = "role_id")
    @ManyToOne
    private RoleEntity role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getRole() {
        return role.getRole();
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

}
