package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SubBill {
    protected BigInteger subBillId;
    protected Double balance;
    protected CommunalUtility communalUtility;

    public SubBill(BigInteger subBillId, Double balance, CommunalUtility communalUtility) {
        this.subBillId = subBillId;
        this.balance = balance;
        this.communalUtility = communalUtility;
    }

    public SubBill() {
    }
}
