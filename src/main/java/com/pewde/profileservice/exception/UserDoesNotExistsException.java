package com.pewde.profileservice.exception;

public class UserDoesNotExistsException extends BadRequestException{

    public UserDoesNotExistsException(){
        super("Пользователя с таким уникальным идентификатором не существует");
    }

}
