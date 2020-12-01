package com.netcracker.model;

import lombok.Data;

@Data
public class ManagerBill {
    private long managerBillId;
    private Manager manager;
    private String cardNumber;
}
