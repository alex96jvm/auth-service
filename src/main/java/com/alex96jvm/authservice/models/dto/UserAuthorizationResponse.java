package com.alex96jvm.authservice.models.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record UserAuthorizationResponse(UUID id, String login, String role, @JsonIgnore String token) {
}
