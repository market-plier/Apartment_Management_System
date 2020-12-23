package com.netcracker.exception;

public class InsufficientBalanceException extends IllegalArgumentException {

    private String errorCode;

    public InsufficientBalanceException(String s) {
        super(s);
    }

    public InsufficientBalanceException(String s, String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

}
