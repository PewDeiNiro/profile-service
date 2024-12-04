package com.pewde.profileservice.exception;

public class PostDoesNotExistsException extends BadRequestException{

    public PostDoesNotExistsException(){
        super("Поста с таким уникальным идентификатором не существует");
    }

}
