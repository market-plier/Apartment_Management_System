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


    public Account getAccountByEmail(String email) throws DaoAccessException {
        return accountDao.getAccountByEmail(email);
    }

    public Account getAccountById(BigInteger accountId) throws DaoAccessException {
        return accountDao.getAccount(accountId);
    }
}
