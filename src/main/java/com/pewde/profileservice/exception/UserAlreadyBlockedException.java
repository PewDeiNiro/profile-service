package com.pewde.profileservice.exception;

public class UserAlreadyBlockedException extends BadRequestException{

    public UserAlreadyBlockedException() {
        super("Пользователь уже заблокирован");
    }

}
