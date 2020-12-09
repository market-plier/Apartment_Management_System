package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ManagerBill {
    private BigInteger managerBillId;
    private Manager manager;
    private String cardNumber;


    public ManagerBill(BigInteger managerBillId, Manager manager, String cardNumber) {
        this.managerBillId = managerBillId;
        this.manager = manager;
        this.cardNumber = cardNumber;
    }
}
