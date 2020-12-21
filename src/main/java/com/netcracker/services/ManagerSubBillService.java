package com.netcracker.services;

import com.netcracker.dao.ManagerSubBillDao;
import com.netcracker.models.CommunalUtility;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.models.ManagerSubBill;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collection;

@Log4j
@Service
@Transactional
public class ManagerSubBillService {

    private final ManagerSubBillDao managerSubBillDao;
    private final ManagerInfoService managerInfoService;

    @Autowired
    public ManagerSubBillService(ManagerSubBillDao managerSubBillDao, ManagerInfoService managerInfoService) {
        this.managerSubBillDao = managerSubBillDao;
        this.managerInfoService = managerInfoService;
    }


    public Collection<ManagerSubBill> getAllManagerSubBill() {
        return managerSubBillDao.getAllManagerSubBills();
    }

    public ManagerSubBill getManagerSubBill(BigInteger id) {
        return managerSubBillDao.getManagerSubBillById(id);
    }

    public void createManagerSubBill(CommunalUtility communalUtility) {
        Manager manager = managerInfoService.getManager();

        managerSubBillDao.createManagerSubBill(new ManagerSubBillBuilder()
                .withManager(manager)
                .withCommunalUtility(communalUtility)
                .build());
    }


    public void updateManagerSubBill(ManagerSubBill managerSubBill) {

    }

    public ManagerSubBill getManagerSubBillByCommunalUtilityId(BigInteger id) {
        return null;
    }

    public void updateManagerSubBillbyManagerOperation(ManagerSpendingOperation managerSpendingOperation) {

    }
}


