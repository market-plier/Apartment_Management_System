package com.netcracker.models;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.math.BigInteger;


@Data
public class Manager extends Account {


    protected ManagerBill managerBill;

    public Manager(BigInteger accountId, String firstName, String lastName, String email,
                   String password, String phoneNumber, Role role,
                   ManagerBill managerBill) {
        super(accountId, firstName, lastName, email, password, phoneNumber, role);
        this.managerBill = managerBill;
    }

}