package com.alex96jvm.authservice.controllers;

import com.alex96jvm.authservice.errors.ErrorsPresentation;
import com.alex96jvm.authservice.exceptions.UserAlreadyExistException;
import com.alex96jvm.authservice.exceptions.UserNotFoundOrPasswordWrongException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<ErrorsPresentation> handleBindException(BindException ex) {
        List<String> errors = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorsPresentation(errors));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<ErrorsPresentation> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorsPresentation(List.of(ex.getMessage())));
    }
    @ExceptionHandler(UserNotFoundOrPasswordWrongException.class)
    @ResponseBody
    public ResponseEntity<ErrorsPresentation> handleUserNotFoundOrPasswordWrongException(UserNotFoundOrPasswordWrongException ex) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorsPresentation(List.of(ex.getMessage())));
    }
}

