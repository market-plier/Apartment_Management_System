package com.netcracker.models;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@Data
public class ManagerSubBill extends SubBill {

    @NotNull(message = "Manager cant be null")
    private Manager manager;
    private List<ManagerSpendingOperation> managerSpendingOperations;
    private List<DebtPaymentOperation> debtPaymentOperations;


    public ManagerSubBill(BigInteger subBillId, Double balance, CommunalUtility communalUtility,
                          List<ManagerSpendingOperation> managerSpendingOperations,
                          List<DebtPaymentOperation> debtPaymentOperations, Manager manager) {
        super(subBillId, balance, communalUtility);
        this.managerSpendingOperations = managerSpendingOperations;
        this.debtPaymentOperations = debtPaymentOperations;
        this.manager = manager;
    }

    public ManagerSubBill() {

    }
}
