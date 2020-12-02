package com.netcracker.dao;

import com.netcracker.models.Account;

import java.math.BigInteger;

public interface AccountDao {
    Account getAccount(BigInteger id);
    Account getAccountByEmail();
}
