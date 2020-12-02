package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class AbstractOperation {
    protected BigInteger operationId;
    protected Double sum;
    protected Date createdAt;
}