package com.pewde.profileservice.exception;

public class CommentDoesNotExistsException extends BadRequestException{

    public CommentDoesNotExistsException(){
        super("Комменатрия с таким уникальным идентификатором не существует");
    }

}
