package com.netcracker.services;

import com.netcracker.dao.AccountDao;
import com.netcracker.models.Account;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccountService")
@Log4j
public class AccountService {


    private final AccountDao accountDao;
    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public Account getAccountByEmail(String email) {
        Account account = null;

        try {
            account = accountDao.getAccountByEmail(email);
        } catch (NullPointerException e)
        {
            log.warn(e.getMessage());
        }
        return account;
    }
}
