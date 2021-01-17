package com.netcracker.services;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.ManagerBillDao;
import com.netcracker.dao.ManagerDao;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.exception.ObjectNotUniqueException;
import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import com.netcracker.secutity.jwt.JwtAccount;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service("ManagerInfoService")
@Log4j
public class ManagerInfoService {

    private final ManagerDao managerDao;
    private final AccountDao accountDao;
    private final AccountService accountService;
    private final ManagerBillDao managerBillDao;

    @Autowired
    public ManagerInfoService(ManagerDao managerDao, AccountService accountService,
                              AccountDao accountDao, ManagerBillDao managerBillDao) {
        this.managerDao = managerDao;
        this.accountService = accountService;
        this.accountDao = accountDao;
        this.managerBillDao = managerBillDao;
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
            Manager managerToUpdate = managerDao.getManager();
            Account account = accountDao.getAccount(manager.getAccountId());
            ManagerBill updateManagerBill = manager.getManagerBill();

            account.setEmail(manager.getEmail());
            account.setFirstName(manager.getFirstName());
            account.setLastName(manager.getLastName());
            account.setPhoneNumber(manager.getPhoneNumber());

            managerToUpdate.setEmail(manager.getEmail());
            managerToUpdate.setFirstName(manager.getFirstName());
            managerToUpdate.setLastName((manager.getLastName()));
            managerToUpdate.setPhoneNumber(manager.getPhoneNumber());
            managerToUpdate.setManagerBill(manager.getManagerBill());

            if(isUnique(managerToUpdate)) {
                accountDao.updateAccount(account);
                managerBillDao.updateManagerBill(updateManagerBill);
            }

            return managerToUpdate;
        } catch (NullPointerException e) {
            log.error("IN Service method updateManager: " + e.getMessage());
            throw e;
        }
    }

    public Manager updateManagerPassword(JwtAccount updater, Manager manager) throws DaoAccessException {

        Account acc = accountService.getAccountById(manager.getAccountId());
        if (!updater.getId().equals(acc.getAccountId())
                && !updater.getAuthorities().toString().equals("[ROLE_MANAGER]")) {
            throw new NotBelongToAccountException("Can not change this account password");
        }
        acc.setPassword(manager.getPassword());
        accountDao.updateAccountPassword(acc);
        return manager;
    }


    public ManagerBill updateManagerBill(ManagerBill managerBill) {
        try {
            managerBillDao.updateManagerBill(managerBill);

            return managerBill;
        } catch (NullPointerException e) {
            log.error("IN Service method updateManagerBill: " + e.getMessage());
            throw e;
        }
    }

    private boolean isUnique(Manager manager) {
        List<Account> accounts = accountDao.getAllAccount();

        for (Account a : accounts) {

            if(!a.getAccountId().equals(manager.getAccountId())) {
                if (a.getEmail().equals(manager.getEmail())) {
                    throw new ObjectNotUniqueException("This email is already in use", BigInteger.valueOf(74));
                }
            }
        }

        return true;
    }

}
