package com.netcracker.models;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
public class Account {
    protected BigInteger accountId;
    @NotEmpty
    protected String firstName;
    @NotEmpty
    protected String lastName;
    @Email
    protected String email;
    @Size(min=8)
    protected String password;
    @Pattern(regexp="^([+]?[\\s0-9]+)?(\\d{3}|[(]?[0-9]+[)])?([-]?[\\s]?[0-9])+$")
    @Size(min=13,max=13)
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