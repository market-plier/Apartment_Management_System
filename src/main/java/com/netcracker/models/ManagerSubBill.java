package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ManagerSubBill {
    private List<ManagerOperationSpending> managerOperationSpendings;
    private List<DebtPaymentOperation> debtPaymentOperations;
}
