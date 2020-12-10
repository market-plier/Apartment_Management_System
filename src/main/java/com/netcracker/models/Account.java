package com.netcracker.models;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

@Data
public class Account {
    protected BigInteger accountId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected Role role;

    public Account(BigInteger accountId, String firstName, String lastName,
                   String email, String password, String phoneNumber, Role role) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Account() {
    }
}