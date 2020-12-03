package com.netcracker.models;

import lombok.Data;

@Data
public class Manager extends Account {
    protected ManagerBill managerBill;
}
