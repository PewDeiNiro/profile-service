package com.pewde.profileservice.exception;

public class WallDoesNotExistsException extends BadRequestException{

    public WallDoesNotExistsException() {
        super("Стены с таким уникальным идентификатором не существует");
    }

}
