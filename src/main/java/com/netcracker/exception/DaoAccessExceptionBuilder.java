package com.netcracker.exception;

import java.math.BigInteger;

public class DaoAccessExceptionBuilder {
    private String message;
    private Throwable cause;
    private BigInteger id;
    private BigInteger errorMessage;

    public DaoAccessExceptionBuilder withMessage(String message){
        this.message = message;
        return this;
    }

    public DaoAccessExceptionBuilder withCause(Throwable cause){
        this.cause = cause;
        return this;
    }

    public DaoAccessExceptionBuilder withId(BigInteger id){
        this.id = id;
        return this;
    }

    public DaoAccessExceptionBuilder withErrorMessage(BigInteger errorMessage){
        this.errorMessage = errorMessage;
        return this;
    }

    public DaoAccessException build(){
        return new DaoAccessException(message, id, cause, errorMessage);
    }
}
