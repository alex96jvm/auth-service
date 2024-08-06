package com.alex96jvm.authservice.models.dto;

public record UserAuthorizationRequest(String login, String password) {
}
