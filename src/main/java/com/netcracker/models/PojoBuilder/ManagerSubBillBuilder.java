package com.netcracker.models.PojoBuilder;

import com.netcracker.models.*;

import java.math.BigInteger;
import java.util.List;

public class ManagerSubBillBuilder {
    private BigInteger subBillId;
    private Double balance;
    private List<ManagerSpendingOperation> managerSpendingOperations;
    private List<DebtPaymentOperation> debtPaymentOperations;
    private CommunalUtility communalUtility;
    private Manager manager;


    public ManagerSubBillBuilder withSubBillId(BigInteger subBillId)
    {
        this.subBillId = subBillId;
        return this;
    }

    public ManagerSubBillBuilder withBalance(Double balance)
    {
        this.balance = balance;
        return this;
    }

    public ManagerSubBillBuilder withManagerSpendingOperation(List<ManagerSpendingOperation> managerSpendingOperations)
    {
        this.managerSpendingOperations = managerSpendingOperations;
        return this;
    }

    public ManagerSubBillBuilder withDeptPaymentOperation(List<DebtPaymentOperation> debtPaymentOperations)
    {
        this.debtPaymentOperations = debtPaymentOperations;
        return this;
    }

    public ManagerSubBillBuilder withCommunalUtility(CommunalUtility communalUtility)
    {
        this.communalUtility = communalUtility;
        return this;
    }

    public ManagerSubBillBuilder withManager(Manager manager)
    {
        this.manager = manager;
        return this;
    }

    public ManagerSubBill build()
    {
        return new ManagerSubBill(subBillId,balance,communalUtility,managerSpendingOperations,debtPaymentOperations, manager);
    }


}