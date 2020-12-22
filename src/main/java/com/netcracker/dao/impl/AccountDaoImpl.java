package com.netcracker.dao.impl;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.mapper.AccountMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.Account;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;


@Repository
@Log4j
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Account getAccount(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ID, new AccountMapper(), id);
        } catch (DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_GET_ACCOUNT_BY_ID, id, ErrorCodes._FAIL_TO_SELECT_ACCOUNT);
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Account getAccountByEmail(String email) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_EMAIL, new AccountMapper(), email);
        } catch (DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_GET_ACCOUNT_BY_EMAIL + email, ErrorCodes._FAIL_TO_SELECT_ACCOUNT);
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }

    }
}
