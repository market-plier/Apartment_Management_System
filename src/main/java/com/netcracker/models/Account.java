package com.netcracker.models;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Data
public class Account {
    @Positive(message = "Apartment`s id is not correct")
    protected BigInteger accountId;

    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "First name format is not correct")
    @NotEmpty(message = "First name can not be empty")
    @Size(min = 1, max = 255, message = "First name size is not correct. Character length must be between 1 and 255")
    protected String firstName;

    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Last name format is not correct")
    @NotEmpty(message = "Last name can not be empty")
    @Size(min = 1, max = 255, message = "Last name size is not correct. Character length must be between 1 and 255")
    protected String lastName;

    @NotEmpty(message = "Email can not be empty")
    @Size(max = 255, message = "Email size is not correct. Character length must be between 1 and 255")
    @Pattern(regexp = "^[0-9a-zA-Z]+([0-9a-zA-Z]*[-._+])*[0-9a-zA-Z]+@[0-9a-zA-Z]+([-.][0-9a-zA-Z]+)*([0-9a-zA-Z]*[.])[a-zA-Z]{2,6}$"
            , message = "Email format is incorrect")
    protected String email;

    protected String password;

    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,13}$"
            , message = "Phone number format is incorrect. '+___ _________'")
    @Size(min = 13, max = 13, message = "Apartment`s phone number should contain 13 characters")
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