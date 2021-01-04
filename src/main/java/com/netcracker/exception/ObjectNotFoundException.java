package com.netcracker.exception;

import java.math.BigInteger;

public class ObjectNotFoundException extends DaoAccessException{
    private final BigInteger errorCode;

    public ObjectNotFoundException(String message, BigInteger errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BigInteger getErrorCode() {
        return errorCode;
    }
}
