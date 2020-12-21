package com.netcracker.services;

import com.netcracker.dao.AccountDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("AccountService")
@Log4j
public class AccountService {


    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public Account getAccountByEmail(String email) {

        try {
            return accountDao.getAccountByEmail(email);
        } catch (NullPointerException | DaoAccessException e) {
            log.error("IN Service method getAccountByEmail: " + e.getMessage());
            throw e;
        }
    }

    public Account getAccountById(BigInteger accountId) {

        try {

            return accountDao.getAccount(accountId);
        } catch (NullPointerException | DaoAccessException e) {
            log.error("IN Service method getAccountById: " + e.getMessage());
            throw e;
        }
    }
}
