package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Data
public abstract class AbstractOperation {
    protected BigInteger operationId;
    @NotNull
    protected Double sum;
    protected Date createdAt;

    protected AbstractOperation(){

    }

    protected AbstractOperation(BigInteger operationId, Double sum, Date createdAt){
        this.operationId = operationId;
        this.sum = sum;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractOperation that = (AbstractOperation) o;

        return Objects.equals(operationId, that.operationId);
    }

    @Override
    public int hashCode() {
        return operationId != null ? operationId.hashCode() : 0;
    }
}
