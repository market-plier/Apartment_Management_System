package com.netcracker.exception;

import java.math.BigInteger;

public class NotFoundInformationForReportException extends DaoAccessException{

    private BigInteger errorCode;
    public NotFoundInformationForReportException(String msg) {
        super(msg);
    }

    public NotFoundInformationForReportException(String msg, BigInteger errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BigInteger getErrorCode() {
        return errorCode;
    }
}
