package com.pewde.profileservice.exception;

public class TokenExpiredException extends UnauthorizedException{

    public TokenExpiredException() {
        super("Срок действия токена истек");
    }

}
