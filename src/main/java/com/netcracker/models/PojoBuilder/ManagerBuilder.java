package com.netcracker.models.PojoBuilder;

import com.netcracker.models.*;

import java.math.BigInteger;

public class ManagerBuilder {


    private ManagerBill managerBill;
    private BigInteger accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;



    public ManagerBuilder withManagerBill(ManagerBill managerBill) {
        this.managerBill = managerBill;
        return this;
    }

    public ManagerBuilder withAccountId(BigInteger accountId) {
        this.accountId = accountId;
        return this;
    }

    public ManagerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ManagerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ManagerBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public ManagerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ManagerBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ManagerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Manager build() {
        return new Manager(accountId, firstName, lastName, email, password,
                phoneNumber, role, managerBill);
    }


}
