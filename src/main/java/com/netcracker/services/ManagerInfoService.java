package com.netcracker.services;

import com.netcracker.dao.ManagerDao;

import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ManagerInfoService")
@Log4j
public class ManagerInfoService {

    private final ManagerDao managerDao;
    private final ManagerBillService managerBillService;

    @Autowired
    public ManagerInfoService(ManagerDao managerDao, ManagerBillService managerBillService) {
        this.managerDao = managerDao;
        this.managerBillService = managerBillService;
    }

    public Manager getManager() {
        try {
            return managerDao.getManager();
        } catch (NullPointerException e) {
            log.error("IN Service method getManager: " + e.getMessage());
            throw e;
        }
    }

    public Manager updateManager(Manager manager) {
        try {
            managerDao.updateManager(manager);
            return manager;
        } catch (NullPointerException e) {
            log.error("IN Service method updateManager: " + e.getMessage());
            throw e;
        }
    }

    public ManagerBill updateManagerBill(ManagerBill managerBill) {
        try {
            managerBillService.updateManagerBill(managerBill);
            return managerBill;
        } catch (NullPointerException e) {
            log.error("IN Service method updateManagerBill: " + e.getMessage());
            throw e;
        }
    }

}
