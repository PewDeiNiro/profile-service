package com.pewde.profileservice.exception;

public class UserDoesNotAdminException extends BadRequestException{

    public UserDoesNotAdminException(){
        super("Пользователь не является администратором");
    }

}
