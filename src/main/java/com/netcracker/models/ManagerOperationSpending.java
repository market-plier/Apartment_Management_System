package com.netcracker.models;

import lombok.Data;

@Data
public class ManagerOperationSpending {
    private  ManagerSubBill managerSubBillId;
    private String description;
}