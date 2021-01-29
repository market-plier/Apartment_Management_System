package com.netcracker.services;

import com.netcracker.dao.ManagerSubBillDao;
import com.netcracker.exception.InsufficientBalanceException;
import com.netcracker.models.*;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;



@Log4j
@Service
@Transactional
public class ManagerSubBillService {

    @Autowired
    private ManagerSubBillDao managerSubBillDao;
    @Autowired
    private ManagerInfoService managerInfoService;
    @Autowired
    private DebtPaymentOperationService debtPaymentOperationService;
    @Autowired
    private ManagerOperationSpendingService managerOperationSpendingService;

    public Collection<ManagerSubBill> getAllManagerSubBills() {
        Collection<ManagerSubBill> managerSubBills = managerSubBillDao.getAllManagerSubBills();
        for (ManagerSubBill managerSubBill : managerSubBills) {
            BigInteger managerSubBillId = managerSubBill.getSubBillId();
            managerSubBill.setManagerSpendingOperations(managerOperationSpendingService.getAllManagerOperationBySubBillId(managerSubBillId));
            managerSubBill.setDebtPaymentOperations(debtPaymentOperationService.getDebtPaymentOperationsByManagerSubBillId(managerSubBillId));
        }
        return managerSubBills;
    }

    public Collection<ManagerSubBill> getManagerSubBillByStatus(BigInteger statusId) {

        Collection<ManagerSubBill> managerSubBills;

        if (statusId != null && statusId.equals(BigInteger.valueOf(1))) {
            managerSubBills = managerSubBillDao.getManagerSubBillByStatus(BigInteger.valueOf(3));//Enabled
        } else if (statusId != null && statusId.equals(BigInteger.valueOf(2))) {
            managerSubBills = managerSubBillDao.getManagerSubBillByStatus(BigInteger.valueOf(4));//Disabled
        } else {
            InsufficientBalanceException balanceException = new InsufficientBalanceException("Insufficient funds on the balance sheet");
            log.error("IN Service method updateManagerSubBillByManagerOperation: " + balanceException.getMessage());
            throw balanceException;
        }

        for (ManagerSubBill managerSubBill : managerSubBills) {
            BigInteger managerSubBillId = managerSubBill.getSubBillId();
            managerSubBill.setManagerSpendingOperations(managerOperationSpendingService.getAllManagerOperationBySubBillId(managerSubBillId));
            managerSubBill.setDebtPaymentOperations(debtPaymentOperationService.getDebtPaymentOperationsByManagerSubBillId(managerSubBillId));
        }

        return managerSubBills;
    }

    public Map<ManagerSubBill, Double> getAllSubBillDebt() {
        return managerSubBillDao.getManagerSubBillsDebt();
    }

    public Collection<ManagerSubBill> getAllManagerSubBillsWithoutOperations() {
        return managerSubBillDao.getAllManagerSubBillsWithOutManager();
    }



    public ManagerSubBill getManagerSubBill(BigInteger managerSubBillId) {

        ManagerSubBill managerSubBill = managerSubBillDao.getManagerSubBillById(managerSubBillId);
        managerSubBill.setManagerSpendingOperations(managerOperationSpendingService.getAllManagerOperationBySubBillId(managerSubBillId));
        managerSubBill.setDebtPaymentOperations(debtPaymentOperationService.getDebtPaymentOperationsByManagerSubBillId(managerSubBillId));
        return managerSubBill;
    }

    public ManagerSubBill getManagerSubBillByCommunalUtilityId(BigInteger communalUtilityId) {
        ManagerSubBill managerSubBill = managerSubBillDao.getManagerSubBillByCommunalUtilityId(communalUtilityId);

        BigInteger managerSubBillId = managerSubBill.getSubBillId();
        managerSubBill.setManagerSpendingOperations(managerOperationSpendingService.getAllManagerOperationBySubBillId(managerSubBillId));
        managerSubBill.setDebtPaymentOperations(debtPaymentOperationService.getDebtPaymentOperationsByManagerSubBillId(managerSubBillId));

        return managerSubBill;

    }

    public void createManagerSubBill(CommunalUtility communalUtility) {
        Manager manager = managerInfoService.getManager();

        managerSubBillDao.createManagerSubBill(new ManagerSubBillBuilder()
                .withManager(manager)
                .withCommunalUtility(communalUtility)
                .build());
    }

    public void updateManagerSubBill(ManagerSubBill managerSubBill) {
        managerSubBillDao.updateManagerSubBill(managerSubBill);
    }

    public void updateManagerSubBillByManagerOperation(ManagerSpendingOperation managerSpendingOperation) {
        ManagerSubBill managerSubBill = managerSubBillDao.getManagerSubBillById(managerSpendingOperation.getManagerSubBill().getSubBillId());

        if (managerSubBill.getBalance() >= managerSpendingOperation.getSum()) {
            managerSubBill.setBalance(managerSubBill.getBalance() - managerSpendingOperation.getSum());
            managerSubBillDao.updateManagerSubBill(managerSubBill);
        } else {
            InsufficientBalanceException balanceException = new InsufficientBalanceException("Insufficient funds on the balance sheet");
            log.error("IN Service method updateManagerSubBillByManagerOperation: " + balanceException.getMessage());
            throw balanceException;
        }
    }


    public void updateManagerSubBillSpendingOperation(ManagerSpendingOperation managerSpendingOperation) {

        ManagerSubBill managerSubBill = managerSubBillDao.getManagerSubBillById(managerSpendingOperation.getManagerSubBill().getSubBillId());
        ManagerSpendingOperation oldManagerOperation = managerOperationSpendingService.getManagerSpendingOperation(managerSpendingOperation.getOperationId());

        Double newSpendingOperation = managerSpendingOperation.getSum();
        Double oldSpendingOperation = oldManagerOperation.getSum();

        if (oldManagerOperation.getManagerSubBill().getSubBillId().equals(managerSubBill.getSubBillId())) {

            if (oldSpendingOperation < newSpendingOperation) {
                if (managerSubBill.getBalance() > (newSpendingOperation - oldSpendingOperation)) {
                    managerSubBill.setBalance(managerSubBill.getBalance() - (newSpendingOperation - oldSpendingOperation));
                    managerSubBillDao.updateManagerSubBill(managerSubBill);
                } else {
                    InsufficientBalanceException balanceException = new InsufficientBalanceException("Insufficient funds on the balance sheet");
                    log.error("IN Service method updateManagerSubBillSpendingOperation: " + balanceException.getMessage());
                    throw balanceException;
                }

            }

            if (oldSpendingOperation > newSpendingOperation) {
                managerSubBill.setBalance(managerSubBill.getBalance() + (oldSpendingOperation - newSpendingOperation));
                managerSubBillDao.updateManagerSubBill(managerSubBill);
            }

        }
    }


    public void updateManagerSubBillByDeptPaymentOperationService(DebtPaymentOperation debtPaymentOperation) {
        ManagerSubBill managerSubBill = managerSubBillDao.getManagerSubBillById(debtPaymentOperation.getManagerSubBill().getSubBillId());

        managerSubBill.setBalance(managerSubBill.getBalance() + debtPaymentOperation.getSum());
        managerSubBillDao.updateManagerSubBill(managerSubBill);
    }

}


