package com.pewde.profileservice.exception;

public class UserAlreadyUnblockedException extends BadRequestException{

    public UserAlreadyUnblockedException() {
        super("Пользователь уже разблокирован");
    }

}
