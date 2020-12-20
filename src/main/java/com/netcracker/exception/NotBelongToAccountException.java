package com.netcracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="It is not belong to account")
public class NotBelongToAccountException extends IllegalArgumentException {

    public NotBelongToAccountException(String s) {
        super(s);
    }

    public NotBelongToAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
