package com.netcracker.dao;

import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.ManagerSpendingOperation;

import java.math.BigInteger;
import java.util.List;

public interface DebtPaymentOperationDao {
    List<ManagerSpendingOperation> getAllDebtPaymentOperations();

    DebtPaymentOperation getDebtPaymentOperationById(BigInteger debtPaymentOperationId);

    void createDebtPaymentOperation(DebtPaymentOperation debtPaymentOperation);
}
