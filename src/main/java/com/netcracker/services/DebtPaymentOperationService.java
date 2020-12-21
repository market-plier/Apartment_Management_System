package com.netcracker.services;

import com.netcracker.dao.DebtPaymentOperationDao;
import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.ManagerSubBill;
import com.netcracker.models.PojoBuilder.DebtPaymentOperationBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentId(BigInteger apartmentId) {
        return debtPaymentOperationDao.getDebtPaymentOperationsByApartmentId(apartmentId);
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentSubBillId(BigInteger apartmentSubBillId) {
        return debtPaymentOperationDao.getDebtPaymentOperationsByApartmentSubBillId(apartmentSubBillId);
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByManagerSubBillId(BigInteger managerSubBilId) {
        return debtPaymentOperationDao.getDebtPaymentOperationsByManagerSubBillId(managerSubBilId);
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentId(Date from, Date to, BigInteger apartmentId) {
        return debtPaymentOperationDao.getDebtPaymentOperationsByDateRangeAndApartmentId(from, to, apartmentId);
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentSubBillId(Date from, Date to, BigInteger apartmentSubBillId) {
        return debtPaymentOperationDao.getDebtPaymentOperationsByDateRangeAndApartmentSubBillId(from, to, apartmentSubBillId);
    }

    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndManagerSubBillId(Date from, Date to, BigInteger managerSubBillId) {
        return debtPaymentOperationDao.getDebtPaymentOperationsByDateRangeAndManagerSubBillId(from, to, managerSubBillId);
    }

    public void createDebtPaymentOperation(ApartmentSubBill apartmentSubBill, Double sum) {
        ManagerSubBill managerSubBill = managerSubBillService.getManagerSubBillByCommunalUtilityId(apartmentSubBill.getCommunalUtility().getCommunalUtilityId());
        managerSubBill.setBalance(managerSubBill.getBalance() + sum);

        managerSubBillService.updateManagerSubBill(managerSubBill);
        debtPaymentOperationDao.createDebtPaymentOperation(new DebtPaymentOperationBuilder()
                .withApartmentSubBill(apartmentSubBill)
                .withManagerSubBill(managerSubBill)
                .withSum(sum)
                .build());
    }
}
