package com.pewde.profileservice.exception;

public class WrongOldPasswordException extends BadRequestException{

    public WrongOldPasswordException(){
        super("Неверный старый пароль");
    }

}
