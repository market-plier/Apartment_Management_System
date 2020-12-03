package com.netcracker.models;

import lombok.Data;

@Data
public class ManagerOperationSpending extends AbstractOperation {
    private ManagerSubBill managerSubBillId;
    private String description;
}
