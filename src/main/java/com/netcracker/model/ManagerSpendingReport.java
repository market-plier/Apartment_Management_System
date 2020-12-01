package com.netcracker.model;

import lombok.Data;

import java.util.List;

@Data
public class ManagerSpendingReport {
   //private ManagerSubBill managerSubBill;
    private List<ManagerOperationSpending> managerOperationSpendings;
}
