package com.netcracker.exception;

import java.math.BigInteger;

public final class NotBelongToAccountExceptionBuilder {

    private StackTraceElement[] stackTrace;
    private BigInteger errorCode;
    private BigInteger id;
    private String message;

    public NotBelongToAccountExceptionBuilder() {
    }

    public NotBelongToAccountExceptionBuilder withStackTrace(StackTraceElement[] stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    public NotBelongToAccountExceptionBuilder withErrorCode(BigInteger errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public NotBelongToAccountExceptionBuilder withId(BigInteger id) {
        this.id = id;
        return this;
    }
    public NotBelongToAccountExceptionBuilder withMessage(String message){
        this.message = message;
        return this;
    }



    public NotBelongToAccountException build() {
        NotBelongToAccountException notBelongToAccountException = new NotBelongToAccountException(message, id, errorCode);
        notBelongToAccountException.setStackTrace(stackTrace);
        return notBelongToAccountException;
    }
}
