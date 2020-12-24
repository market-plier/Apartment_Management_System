package com.netcracker.services;

import com.netcracker.dao.ManagerBillDao;
import com.netcracker.models.ManagerBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("ManagerBillService")
@Log4j
public class ManagerBillService {

    private final ManagerBillDao managerBillDao;

    @Autowired
    public ManagerBillService(ManagerBillDao managerBillDao) {
        this.managerBillDao = managerBillDao;
    }

    public ManagerBill getManagerBillById(BigInteger id) {
        try {
            return managerBillDao.getManagerBillById(id);
        } catch (NullPointerException e) {
            log.error("IN Service method createManagerBill: " + e.getMessage());
            throw e;
        }
    }

    public void updateManagerBill(ManagerBill managerBill) {
        try {
            managerBillDao.updateManagerBill(managerBill);
        } catch (NullPointerException e) {
            log.error("IN Service method updateManagerBill: " + e.getMessage());
            throw e;
        }
    }

    public void createManagerBill(ManagerBill managerBill) {
        try {
            managerBillDao.createManagerBill(managerBill);
        } catch (NullPointerException e) {
            log.error("IN Service method createManagerBill: " + e.getMessage());
            throw e;
        }
    }


}
