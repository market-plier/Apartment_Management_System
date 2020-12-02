package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Account {
    protected BigInteger accountId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected Role Role;
}
