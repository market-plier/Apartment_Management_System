package com.netcracker.services;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.ManagerDao;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.models.Account;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("ManagerInfoService")
@Log4j
public class ManagerInfoService {

    private final ManagerDao managerDao;
    private final ManagerBillService managerBillService;
    private final AccountDao accountDao;
    private final AccountService accountService;

    @Autowired
    public ManagerInfoService(ManagerDao managerDao, ManagerBillService managerBillService, AccountService accountService,
                              AccountDao accountDao) {
        this.managerDao = managerDao;
        this.managerBillService = managerBillService;
        this.accountService = accountService;
        this.accountDao = accountDao;
    }

    public Manager getManager() {
        return managerDao.getManager();
    }

    public Manager updateManager(Manager manager) {

        Account account = accountDao.getAccount(manager.getAccountId());

        account.setEmail(manager.getEmail());
        account.setFirstName(manager.getFirstName());
        account.setLastName(manager.getLastName());
        account.setPhoneNumber(manager.getPhoneNumber());

        managerDao.updateManager(manager);
        accountDao.updateAccount(account);

        return manager;
    }

    public Manager updateManagerPassword(Manager manager) throws DaoAccessException {
        Account account = accountService.getAccountByEmail(manager.getEmail());
        if (!account.getAccountId().equals(manager.getAccountId())) {
            throw new NotBelongToAccountException("Can not change this account password");
        }

        account.setPassword(manager.getPassword());
        accountDao.updateAccount(account);
        return manager;

    }

    public ManagerBill updateManagerBill(ManagerBill managerBill) {
        managerBillService.updateManagerBill(managerBill);
        return managerBill;
    }

}
