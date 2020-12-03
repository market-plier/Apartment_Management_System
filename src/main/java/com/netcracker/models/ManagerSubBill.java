package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ManagerSubBill extends SubBill {
    private List<ManagerSpendingOperation> managerSpendingOperations;
    private List<DebtPaymentOperation> debtPaymentOperations;
}
