package com.pewde.profileservice.service;

import com.pewde.profileservice.entity.Token;
import com.pewde.profileservice.entity.User;
import com.pewde.profileservice.exception.InvalidTokenException;
import com.pewde.profileservice.exception.TokenExpiredException;

import java.util.Date;

public class AuthService {

    public static void checkAuth(User user, String token) {
        token = token.replace("Bearer ", "");
        Token userToken = user.getToken();
        if (!userToken.getToken().equals(token)) {
            throw new InvalidTokenException();
        }
        if (userToken.getExpiry().before(new Date())){
            throw new TokenExpiredException();
        }
    }

}
