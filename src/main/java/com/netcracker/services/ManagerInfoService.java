package com.netcracker.services;

import com.netcracker.dao.ManagerDao;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Apartment;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ManagerInfoService")
@Log4j
public class ManagerInfoService {

    @Autowired
    ManagerDao managerDao;

    ManagerBillService managerBillService;

    public Manager getManager() {
        return managerDao.getManager();
    }

    public Manager updateManager(Manager manager) {
        managerDao.updateManager(manager);
        return manager;
    }

    public ManagerBill updateManagerBill(ManagerBill managerBill){
        managerBillService.updateManagerBill(managerBill);
        return managerBill;
    }

}
