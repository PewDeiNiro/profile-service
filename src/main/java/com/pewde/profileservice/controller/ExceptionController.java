package com.pewde.profileservice.controller;

import com.pewde.profileservice.exception.HttpException;
import com.pewde.profileservice.mapper.ExceptionMapper;
import com.pewde.profileservice.response.ExceptionResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class ExceptionController {

    @Autowired
    private ExceptionMapper exceptionMapper;

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ExceptionResponse> handleException(HttpException exception) {
        ExceptionResponse response = exceptionMapper.mapExceptionToExceptionResponse(exception);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
