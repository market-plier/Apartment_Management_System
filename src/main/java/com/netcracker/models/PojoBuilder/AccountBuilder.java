package com.netcracker.models.PojoBuilder;

import com.netcracker.models.Account;
import com.netcracker.models.Role;

import java.math.BigInteger;

public class AccountBuilder{
    protected BigInteger accountId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected Role role;


    public AccountBuilder withAccountId(BigInteger accountId)
    {
        this.accountId = accountId;
        return this;
    }

    public AccountBuilder withFirstName(String firstName)
    {
        this.firstName = firstName;
        return  this;
    }
    public AccountBuilder withLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public AccountBuilder withRole(Role role)
    {
        this.role = role;
        return this;
    }

    public AccountBuilder withEmail(String email)
    {
        this.email = email;
        return this;
    }

    public AccountBuilder withPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AccountBuilder withPassword(String password)
    {
        this.password = password;
        return  this;
    }


    public Account build()
    {
        return new Account(accountId, firstName, lastName, email, password, phoneNumber, role);
    }



}
