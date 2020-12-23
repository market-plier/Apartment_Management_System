package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
public class ManagerBill {
    private BigInteger managerBillId;
    @NotNull(message = "Manager cant be null")
    private Manager manager;
    @NotBlank
    @Size(min = 16, max = 16, message = "Length card number must be 16 ")
    private String cardNumber;


    public ManagerBill(BigInteger managerBillId, Manager manager, String cardNumber) {
        this.managerBillId = managerBillId;
        this.manager = manager;
        this.cardNumber = cardNumber;
    }
}
