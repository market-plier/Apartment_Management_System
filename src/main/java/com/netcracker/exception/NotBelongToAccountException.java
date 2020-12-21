package com.netcracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigInteger;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="It is not belong to account")
public class NotBelongToAccountException extends IllegalArgumentException {

    private BigInteger errorCode;

    public NotBelongToAccountException(String s) {
        super(s);
    }

    public NotBelongToAccountException(String s, BigInteger errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public NotBelongToAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public BigInteger getErrorCode() {
        return errorCode;
    }
}
