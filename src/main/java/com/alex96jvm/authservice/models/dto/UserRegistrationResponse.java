package com.alex96jvm.authservice.models.dto;

import java.util.UUID;

public record UserRegistrationResponse(UUID id, String login) {

}
