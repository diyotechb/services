package com.diyo.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserIdException extends RuntimeException{
    public InvalidUserIdException(String message){
        super(message);
    }
}
