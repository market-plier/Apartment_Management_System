package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ManagerSubBill extends SubBill {
    private List<ManagerOperationSpending> managerOperationSpendings;
    private List<DebtPaymentOperation> debtPaymentOperations;
}
