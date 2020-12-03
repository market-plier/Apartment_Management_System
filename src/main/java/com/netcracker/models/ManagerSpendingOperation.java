package com.netcracker.models;

import lombok.Data;

@Data
public class ManagerSpendingOperation extends AbstractOperation {
    private ManagerSubBill managerSubBillId;
    private String description;
}
