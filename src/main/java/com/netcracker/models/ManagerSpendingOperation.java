package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class ManagerSpendingOperation extends AbstractOperation {

    private String description;
    private ManagerSubBill managerSubBill;

    public ManagerSpendingOperation(BigInteger operationId, Double sum, Date createdAt, String description, ManagerSubBill managerSubBill) {
        super(operationId, sum, createdAt);
        this.description = description;
        this.managerSubBill = managerSubBill;
    }
}
