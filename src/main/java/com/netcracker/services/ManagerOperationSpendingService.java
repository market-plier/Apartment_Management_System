package com.netcracker.services;

import com.netcracker.dao.ManagerSpendingOperationDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.models.ManagerSubBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Log4j
public class ManagerOperationSpendingService {

    private final ManagerSpendingOperationDao managerSpendingOperationDao;
    private final ManagerSubBillService managerSubBillService;


    @Autowired
    public ManagerOperationSpendingService(ManagerSpendingOperationDao managerSpendingOperationDao, ManagerSubBillService managerSubBillService) {
        this.managerSpendingOperationDao = managerSpendingOperationDao;
        this.managerSubBillService = managerSubBillService;
    }


    public void createManagerOperationSpending(ManagerSpendingOperation managerSpendingOperation) throws DaoAccessException {
        try {
            ManagerSubBill managerSubBill = managerSubBillService.getManagerSubBill(managerSpendingOperation.getManagerSubBill().getSubBillId());
            if (managerSubBill.getBalance() >= managerSpendingOperation.getSum()) {
                managerSpendingOperationDao.createManagerOperationSpending(managerSpendingOperation);
                managerSubBillService.updateManagerSubBillByManagerOperation(managerSpendingOperation);
            }

        } catch (NullPointerException e) {
            log.error("IN Service method createManagerOperationSpending: " + e.getMessage());
            throw e;
        }

    }

    public List<ManagerSpendingOperation> getAllManagerOperationByDateAndCommunalUtility(Date start, Date end,
                                                                                         Set<BigInteger> communalUtility) throws DaoAccessException, NullPointerException {
        try {
            return managerSpendingOperationDao.getAllManagerOperationByDateAndCommunalIdList(start, end, communalUtility);
        } catch (NullPointerException e) {
            log.error("IN Service method getAllManagerOperationByDateAndCommunalUtility: " + e.getMessage());
            throw e;
        }

    }


    public List<ManagerSpendingOperation> getAllManagerOperationByDate(Date start, Date end) throws DaoAccessException, NullPointerException {
        try {
            return managerSpendingOperationDao.getAllManagerOperationByDate(start, end);
        } catch (NullPointerException e) {
            log.error("IN Service method getAllManagerOperationByDate: " + e.getMessage());
            throw e;
        }


    }

    public ManagerSpendingOperation getManagerSpendingOperation(BigInteger id) throws DaoAccessException, NullPointerException {
        try {
            return managerSpendingOperationDao.getManagerOperationSpendingById(id);
        } catch (NullPointerException e) {
            log.error("IN Service method getManagerSpendingOperation: " + e.getMessage());
            throw e;
        }
    }

    public void updateManagerOperation(ManagerSpendingOperation managerSpendingOperation) throws DaoAccessException, NullPointerException {
        try {
            if (managerSpendingOperationDao.getManagerOperationSpendingById(managerSpendingOperation.getOperationId()) != null) {
                managerSpendingOperationDao.updateManagerOperationSpending(managerSpendingOperation);
            }
        } catch (NullPointerException e) {
            log.error("IN Service method updateManagerOperation: " + e.getMessage());
            throw e;
        }

    }


}
