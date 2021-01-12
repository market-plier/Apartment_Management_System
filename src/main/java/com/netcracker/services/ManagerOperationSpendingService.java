package com.netcracker.services;

import com.netcracker.dao.ManagerSpendingOperationDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.InsufficientBalanceException;
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
    @Autowired
    private ManagerSubBillService managerSubBillService;


    @Autowired
    public ManagerOperationSpendingService(ManagerSpendingOperationDao managerSpendingOperationDao) {
        this.managerSpendingOperationDao = managerSpendingOperationDao;
    }


    public void createManagerOperationSpending(ManagerSpendingOperation managerSpendingOperation) throws DaoAccessException {
        ManagerSubBill managerSubBill = managerSubBillService.getManagerSubBill(managerSpendingOperation.getManagerSubBill().getSubBillId());
        if (managerSubBill.getBalance() >= managerSpendingOperation.getSum()) {
            managerSpendingOperationDao.createManagerOperationSpending(managerSpendingOperation);
            managerSubBillService.updateManagerSubBillByManagerOperation(managerSpendingOperation);
        } else {
            InsufficientBalanceException balanceException = new InsufficientBalanceException("Not enough money");
            log.error("IN Service method createManagerOperationSpending: " + balanceException.getMessage());
            throw balanceException;
        }
    }

    public List<ManagerSpendingOperation> getAllManagerOperationByDateAndCommunalUtility(Date start, Date end,
                                                                                         Set<BigInteger> communalUtility) throws DaoAccessException {
        return managerSpendingOperationDao.getAllManagerOperationByDateAndCommunalIdList(start, end, communalUtility);

    }

    public List<ManagerSpendingOperation> getAllManagerOperationByCommunalUtility(Set<BigInteger> communalUtility)
            throws DaoAccessException {
        return managerSpendingOperationDao.getAllManagerOperationSpendingSortedByCommunalNameList(communalUtility);

    }


    public List<ManagerSpendingOperation> getAllManagerOperationByDate(Date start, Date end) throws DaoAccessException {
       return managerSpendingOperationDao.getAllManagerOperationByDate(start, end);

    }

    public ManagerSpendingOperation getManagerSpendingOperation(BigInteger id) throws DaoAccessException {

        return managerSpendingOperationDao.getManagerOperationSpendingById(id);
    }

    public void updateManagerOperation(ManagerSpendingOperation managerSpendingOperation) throws DaoAccessException{

        managerSubBillService.updateManagerSubBillSpendingOperation(managerSpendingOperation);
        managerSpendingOperationDao.updateManagerOperationSpending(managerSpendingOperation);

    }

    public List<ManagerSpendingOperation> getAllManagerOperationBySubBillId(BigInteger id) {
        return managerSpendingOperationDao.getAllManagerOperationSpendingByManagerSubBill(id);
    }


}
