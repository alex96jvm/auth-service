package com.alex96jvm.authservice.models.entities;
public enum RoleType {
    ADMIN_ROLE(1),
    USER_ROLE(2);

    private final int id;

    RoleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
