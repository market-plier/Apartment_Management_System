package com.netcracker.exception;

import java.math.BigInteger;

public class ObjectNotUniqueException extends IllegalArgumentException{
    private final BigInteger errorCode;

    public ObjectNotUniqueException(String message, BigInteger errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BigInteger getErrorCode() {
        return errorCode;
    }
}
