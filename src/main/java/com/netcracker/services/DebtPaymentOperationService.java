package com.netcracker.services;

import com.netcracker.dao.DebtPaymentOperationDao;
import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.ManagerSubBill;
import com.netcracker.models.PojoBuilder.DebtPaymentOperationBuilder;
import com.netcracker.services.exceptions.CreationException;
import com.netcracker.services.exceptions.DataNotFoundException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Log4j
public class DebtPaymentOperationService {
    private final DebtPaymentOperationDao debtPaymentOperationDao;
    private final ManagerSubBillService managerSubBillService;

    @Autowired
    public DebtPaymentOperationService(DebtPaymentOperationDao debtPaymentOperationDao, ManagerSubBillService managerSubBillService) {
        this.debtPaymentOperationDao = debtPaymentOperationDao;
        this.managerSubBillService = managerSubBillService;
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentId(BigInteger apartmentId) throws DataNotFoundException {
        try {
            return debtPaymentOperationDao.getDebtPaymentOperationsByApartmentId(apartmentId);
        } catch (DataAccessException e) {
            log.warn("Error getting debt payment operations by apartment_id = " + apartmentId + " :", e);
            throw new DataNotFoundException(e.getMessage(), ErrorCodes._FAIL_TO_SELECT);
        }
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentSubBillId(BigInteger apartmentSubBillId) throws DataNotFoundException {
        try {
            return debtPaymentOperationDao.getDebtPaymentOperationsByApartmentSubBillId(apartmentSubBillId);
        } catch (DataAccessException e) {
            log.warn("Error getting debt payment operations by apartment_sub_bill_id = " + apartmentSubBillId + " :", e);
            throw new DataNotFoundException(e.getMessage(), ErrorCodes._FAIL_TO_SELECT);
        }
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByManagerSubBillId(BigInteger managerSubBilId) throws DataNotFoundException {
        try {
            return debtPaymentOperationDao.getDebtPaymentOperationsByManagerSubBillId(managerSubBilId);
        } catch (DataAccessException e) {
            log.warn("Error getting debt payment operations by manager_sub_bill_id = " + managerSubBilId + " :", e);
            throw new DataNotFoundException(e.getMessage(), ErrorCodes._FAIL_TO_SELECT);
        }
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentId(Date from, Date to, BigInteger apartmentId) throws DataNotFoundException {
        try {
            return debtPaymentOperationDao.getDebtPaymentOperationsByDateRangeAndApartmentId(from, to, apartmentId);
        } catch (DataAccessException e) {
            log.warn("Error getting debt payment operations by date range from " + from + " to " + to + " and apartment_id = " + apartmentId + " :", e);
            throw new DataNotFoundException(e.getMessage(), ErrorCodes._FAIL_TO_SELECT);
        }
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentSubBillId(Date from, Date to, BigInteger apartmentSubBillId) throws DataNotFoundException {
        try {
            return debtPaymentOperationDao.getDebtPaymentOperationsByDateRangeAndApartmentSubBillId(from, to, apartmentSubBillId);
        } catch (DataAccessException e) {
            log.warn("Error getting debt payment operations by date range from " + from + " to " + to + " and apartment_sub_bill_id = " + apartmentSubBillId + " :", e);
            throw new DataNotFoundException(e.getMessage(), ErrorCodes._FAIL_TO_SELECT);
        }
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndManagerSubBillId(Date from, Date to, BigInteger managerSubBillId) throws DataNotFoundException {
        try {
            return debtPaymentOperationDao.getDebtPaymentOperationsByDateRangeAndManagerSubBillId(from, to, managerSubBillId);
        } catch (DataAccessException e) {
            log.warn("Error getting debt payment operations by date range from " + from + " to " + to + " and manager_sub_bill_id = " + managerSubBillId + " :", e);
            throw new DataNotFoundException(e.getMessage(), ErrorCodes._FAIL_TO_SELECT);
        }
    }

    public void createDebtPaymentOperation(ApartmentSubBill apartmentSubBill, Double sum) throws CreationException {
        try {
            ManagerSubBill managerSubBill = managerSubBillService.getManagerSubBillByCommunalUtilityId(apartmentSubBill.getCommunalUtility().getCommunalUtilityId());
            managerSubBill.setBalance(managerSubBill.getBalance() + sum);

            managerSubBillService.updateManagerSubBill(managerSubBill);
            debtPaymentOperationDao.createDebtPaymentOperation(new DebtPaymentOperationBuilder()
                    .withApartmentSubBill(apartmentSubBill)
                    .withManagerSubBill(managerSubBill)
                    .withSum(sum)
                    .build());
        } catch (DataAccessException e) {
            log.warn("Error creating debt payment operation with apartment_sub_bill = " + apartmentSubBill + " :", e);
            throw new CreationException(e.getMessage(), ErrorCodes._FAIL_TO_INSERT);
        }
    }
}
