package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class SubBill {
    protected BigInteger subBillId;
    protected Double balance;
}
