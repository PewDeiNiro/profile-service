package com.pewde.profileservice.exception;

public class CommentDoesNotBelongToUserException extends BadRequestException{

    public CommentDoesNotBelongToUserException(){
        super("Комментарий не принадлежит пользователю");
    }

}
