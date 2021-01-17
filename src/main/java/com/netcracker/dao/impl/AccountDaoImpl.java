package com.netcracker.dao.impl;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.mapper.AccountMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.Account;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;


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
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_ACCOUNT)
                    .withMessage(EXCEPTION_GET_ACCOUNT_BY_ID)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAccount " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public Account getAccountByEmail(String email) throws DataAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_EMAIL, new AccountMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_ACCOUNT)
                    .withMessage(EXCEPTION_GET_ACCOUNT_BY_EMAIL + email)
                    .withCause(e.getCause())
                    .build();
            log.error("getAccountByEmail " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public List<Account> getAllAccount() throws DaoAccessException {
        try {
            List<Account> list = jdbcTemplate.query(GET_ALL_ACCOUNT, new AccountMapper());
            if (list.isEmpty()) {
                throw new NotFoundException(EXCEPTION_NO_ACCOUNT_WERE_FOUND);
            }
            return list;
        } catch (DataAccessException | NotFoundException e) {
            DaoAccessException accessException = new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_ACCOUNT)
                    .withMessage(EXCEPTION_GET_ALL_ACCOUNTS)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllAccount " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public Account updateAccountPassword(Account account) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_ACCOUNT_PASSWORD,
                    account.getPassword(),
                    account.getAccountId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_UPDATE_ACCOUNT)
                    .withCause(e.getCause())
                    .withId(account.getAccountId())
                    .withErrorMessage(BigInteger.valueOf(72))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        return account;
    }

    @Override
    public Account updateAccount(Account account) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_ACCOUNT,
                    account.getEmail(),
                    account.getLastName(),
                    account.getFirstName(),
                    account.getPhoneNumber(),
                    account.getAccountId());
            jdbcTemplate.update("commit");
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_UPDATE_ACCOUNT)
                    .withCause(e.getCause())
                    .withId(account.getAccountId())
                    .withErrorMessage(BigInteger.valueOf(72))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        return account;
    }
}
