package com.pewde.profileservice.exception;

public class GroupDoesNotExistsException extends BadRequestException{

    public GroupDoesNotExistsException(){
        super("Группы с таким уникальным идентификатором стены не существует");
    }

}
