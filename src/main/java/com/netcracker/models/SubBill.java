package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "SubBill{" +
                "subBillId=" + subBillId +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubBill subBill = (SubBill) o;

        return Objects.equals(communalUtility, subBill.communalUtility);
    }

    @Override
    public int hashCode() {
        return communalUtility != null ? communalUtility.hashCode() : 0;
    }
}
