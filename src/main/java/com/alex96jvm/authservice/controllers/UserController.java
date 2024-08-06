package com.alex96jvm.authservice.controllers;

import com.alex96jvm.authservice.exceptions.RoleNotFoundException;
import com.alex96jvm.authservice.exceptions.UserAlreadyExistException;
import com.alex96jvm.authservice.exceptions.UserNotFoundOrPasswordWrongException;
import com.alex96jvm.authservice.models.dto.UserAuthorizationRequest;
import com.alex96jvm.authservice.models.dto.UserAuthorizationResponse;
import com.alex96jvm.authservice.models.dto.UserRegistrationResponse;
import com.alex96jvm.authservice.models.dto.UserRegistrationRequest;
import com.alex96jvm.authservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth-service")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Transactional
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request, BindingResult result,
                                                                 UriComponentsBuilder uriComponentsBuilder) throws UserAlreadyExistException, BindException, RoleNotFoundException {
        if (result.hasErrors()) {
           throw new BindException(result);
        }
        UserRegistrationResponse response = userService.createUser(request);
        return ResponseEntity.created(uriComponentsBuilder
                        .path("/api/v1/auth-service/registration/{userId}")
                        .build(Map.of("userId", response.id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

    @PostMapping("/authorization")
    public ResponseEntity<UserAuthorizationResponse> authorizeUser(@RequestBody UserAuthorizationRequest request) throws UserNotFoundOrPasswordWrongException {
                UserAuthorizationResponse response = userService.authorizeUser(request);
                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, response.token())
                        .body(response);
    }
}

