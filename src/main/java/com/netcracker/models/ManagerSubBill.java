package com.netcracker.models;

import lombok.Data;


import java.math.BigInteger;
import java.util.List;

@Data
public class ManagerSubBill extends SubBill {
    private List<ManagerSpendingOperation> managerSpendingOperations;
    private List<DebtPaymentOperation> debtPaymentOperations;

    public ManagerSubBill(BigInteger subBillId, Double balance, CommunalUtility communalUtility,
                          List<ManagerSpendingOperation> managerSpendingOperations,
                          List<DebtPaymentOperation> debtPaymentOperations) {
        super(subBillId, balance, communalUtility);
        this.managerSpendingOperations = managerSpendingOperations;
        this.debtPaymentOperations = debtPaymentOperations;
    }


}
