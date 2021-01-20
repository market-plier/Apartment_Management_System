package com.netcracker.exception;

public class IllegalSumToPayException extends IllegalArgumentException{
    private String errorCode;

    public IllegalSumToPayException(String s) {
        super(s);
    }

    public IllegalSumToPayException(String s, String errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public IllegalSumToPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
