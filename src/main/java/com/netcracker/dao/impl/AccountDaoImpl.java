package com.netcracker.dao.impl;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.mapper.AccountMapper;
import com.netcracker.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;


@Repository
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Account getAccount(BigInteger id) {
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ID, new AccountMapper(),id);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_EMAIL,new AccountMapper(), email);
    }
}
