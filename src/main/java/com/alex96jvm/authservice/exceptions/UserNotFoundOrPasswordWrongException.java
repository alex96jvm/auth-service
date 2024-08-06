package com.alex96jvm.authservice.exceptions;

public class UserNotFoundOrPasswordWrongException extends Exception{
    public UserNotFoundOrPasswordWrongException(){
        super("Пользователь с таким логином или паролем не существует");
    }
}
