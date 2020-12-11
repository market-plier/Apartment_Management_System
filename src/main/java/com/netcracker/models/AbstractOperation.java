package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public abstract class AbstractOperation {
    protected BigInteger operationId;
    protected Double sum;
    protected Date createdAt;

    protected AbstractOperation(){

    }

    protected AbstractOperation(BigInteger operationId, Double sum, Date createdAt){
        this.operationId = operationId;
        this.sum = sum;
        this.createdAt = createdAt;
    }
}
