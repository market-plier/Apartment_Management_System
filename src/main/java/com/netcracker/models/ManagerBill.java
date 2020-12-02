package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ManagerBill {
    private BigInteger managerBillId;
    private Manager manager;
    private String cardNumber;
}
