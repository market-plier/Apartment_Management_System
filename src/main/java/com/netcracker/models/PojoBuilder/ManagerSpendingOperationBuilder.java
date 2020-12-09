package com.netcracker.models.PojoBuilder;

import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.models.ManagerSubBill;

import java.math.BigInteger;
import java.util.Date;

public class ManagerSpendingOperationBuilder {
    private BigInteger operationId;
    private Double sum;
    private Date createdAt;
    private ManagerSubBill managerSubBill;
    private String description;


    public ManagerSpendingOperationBuilder withOperationId(BigInteger operationId)
    {
        this.operationId = operationId;
        return this;
    }
    public ManagerSpendingOperationBuilder withSum(Double sum)
    {
        this.sum = sum;
        return this;
    }

    public ManagerSpendingOperationBuilder withCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
        return this;
    }

    public ManagerSpendingOperationBuilder withManagerSubBill(ManagerSubBill managerSubBill)
    {
        this.managerSubBill = managerSubBill;
        return this;
    }

    public ManagerSpendingOperationBuilder withDescription(String description)
    {
        this.description = description;
        return this;
    }


    public ManagerSpendingOperation build()
    {
        return new ManagerSpendingOperation(operationId,sum,createdAt,description,managerSubBill);
    }


}
