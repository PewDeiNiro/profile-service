package com.pewde.profileservice.exception;

public class PostDoesNotBelongToUserException extends BadRequestException{

    public PostDoesNotBelongToUserException(){
        super("Пост не принадлежит пользователю");
    }

}
