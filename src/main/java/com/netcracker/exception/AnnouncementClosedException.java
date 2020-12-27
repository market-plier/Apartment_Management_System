package com.netcracker.exception;

import java.math.BigInteger;

public class AnnouncementClosedException extends IllegalArgumentException {
    private BigInteger errorCode;

    public AnnouncementClosedException(String s, BigInteger errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
}
