package com.netcracker.dao;

import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.ManagerOperationSpending;

import java.math.BigInteger;
import java.util.List;

public interface DebtPaymentOperationDao {
    List<ManagerOperationSpending> getAllDebtPaymentOperations();

    DebtPaymentOperation getDebtPaymentOperationById(BigInteger debtPaymentOperationId);

    void createDebtPaymentOperation(DebtPaymentOperation debtPaymentOperation);
}
