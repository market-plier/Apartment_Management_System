package com.netcracker.exception;

import java.math.BigInteger;

public interface ErrorCodes {
    BigInteger _FAIL_TO_INSERT = BigInteger.valueOf(1);
    BigInteger _FAIL_TO_UPDATE = BigInteger.valueOf(2);
    BigInteger _FAIL_TO_SELECT = BigInteger.valueOf(3);
}
