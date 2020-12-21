package com.netcracker.exception;

import org.springframework.dao.DataAccessException;

import java.math.BigInteger;

public class DaoAccessException extends DataAccessException {

    private BigInteger errorMessage;

    public DaoAccessException(String msg) {
        super(msg);
    }

    public DaoAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DaoAccessException(String msg,  BigInteger id, Throwable cause, BigInteger errorMessage) {
        super(createMessage(msg, id), cause);
        this.errorMessage = errorMessage;
    }

    public DaoAccessException(String msg,  BigInteger id, BigInteger errorMessage) {
        super(createMessage(msg, id));
        this.errorMessage = errorMessage;
    }


    public DaoAccessException(String msg,  BigInteger errorMessage) {
        super(msg);
        this.errorMessage = errorMessage;
    }

    public DaoAccessException(String msg, BigInteger id, Throwable cause) {
        super(createMessage(msg, id), cause);
    }

    private static String createMessage(String msg, BigInteger id) {
        return msg + " { " + id + " } ";
    }

    public BigInteger getErrorMessage() {
        return errorMessage;
    }
}
