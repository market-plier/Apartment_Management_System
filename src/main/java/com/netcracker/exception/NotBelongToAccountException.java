package com.netcracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="It is not belong to account")
public class NotBelongToAccountException extends IllegalArgumentException {

    private String errorCode;

    public NotBelongToAccountException(String s) {
        super(s);
    }

    public NotBelongToAccountException(String s, String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public NotBelongToAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
