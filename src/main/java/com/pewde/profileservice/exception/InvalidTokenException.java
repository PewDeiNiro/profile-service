package com.pewde.profileservice.exception;

public class InvalidTokenException extends UnauthorizedException{

    public InvalidTokenException(){
        super("Невалидный токен пользователя");
    }

}
