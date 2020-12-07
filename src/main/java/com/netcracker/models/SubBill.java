package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SubBill {
    protected BigInteger subBillId;
    protected Double balance;

    public SubBill(BigInteger subBillId, Double balance) {
        this.subBillId = subBillId;
        this.balance = balance;
    }

    public SubBill() {
    }
}
