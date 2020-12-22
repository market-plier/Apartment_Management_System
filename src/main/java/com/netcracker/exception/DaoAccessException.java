package com.netcracker.exception;

import org.springframework.dao.DataAccessException;

import java.math.BigInteger;

public class DaoAccessException extends DataAccessException {

    private BigInteger errorMessage;

    public DaoAccessException(String msg,  BigInteger id, Throwable cause, BigInteger errorMessage) {
        super(createMessage(msg, id), cause);
        this.errorMessage = errorMessage;
    }

    private static String createMessage(String msg, BigInteger id) {
        return msg + " { " + id + " } ";
    }

    public BigInteger getErrorMessage() {
        return errorMessage;
    }
}
