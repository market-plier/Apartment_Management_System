package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Data
public class ManagerBill {

    @NotNull(message = "Id cant be null")
    @Positive()
    private BigInteger managerBillId;

    private Manager manager;

    @NotNull(message = "Card Number cant be null")
    @NotBlank(message = "Card Number cant be blank")
    @Pattern(regexp = "(\\d{4}([-]|)\\d{4}([-]|)\\d{4}([-]|)\\d{4})", message = "Card Number must be integer value. MasterCard or Visa")
    @Size(min = 19, max = 19, message = "Length card number must be 16 ")
    private String cardNumber;

    public ManagerBill(BigInteger managerBillId, Manager manager, String cardNumber) {
        this.managerBillId = managerBillId;
        this.manager = manager;
        this.cardNumber = cardNumber;
    }
}
