package com.netcracker.model;

import lombok.Data;

@Data
public class Account {
    protected long accountId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected Role Role;
}
