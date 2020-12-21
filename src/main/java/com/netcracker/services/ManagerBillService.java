package com.netcracker.services;

import com.netcracker.dao.ManagerBillDao;
import com.netcracker.dao.ManagerDao;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("ManagerBillService")
@Log4j
public class ManagerBillService {

    @Autowired
    ManagerBillDao managerBillDao;

    public ManagerBill getManagerBillById(BigInteger id){
        return managerBillDao.getManagerBillById(id);
    }

    public void updateManagerBill(ManagerBill managerBill) {
        managerBillDao.updateManagerBill(managerBill);
    }


    public void createManagerBill(ManagerBill managerBill) {
        managerBillDao.createManagerBill(managerBill);
    }


}
